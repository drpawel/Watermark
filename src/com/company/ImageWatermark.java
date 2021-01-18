package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Image Watermark class
 */
public class ImageWatermark extends JFrame{
    private ImagePanel imagePanel;
    private BufferedImage sourceImage;
    private BufferedImage watermarkImage = null;
    private JSlider opacitySlider, xSlider, ySlider;

    /**
     * ImageWatermark constructor
     * @param imagePanel
     */
    public ImageWatermark(ImagePanel imagePanel){
        this.imagePanel = imagePanel;
        this.sourceImage = imagePanel.getImage();
        getWatermarkImage();
        if(imagePanel.getImage()!=null){
            this.getContentPane().add(prepareMainPanel());
            setFrame();
        }else{
            com.company.DialogLibrary.showNoImageDialog();
        }

    }

    /**
     * creating MainPanel function
     * @return MainPanel
     */
    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(225,250));

        mainPanel.add(prepareOptionsPanel(),BorderLayout.CENTER);
        mainPanel.add(prepareSubmitButton(),BorderLayout.PAGE_END);
        return mainPanel;
    }

    /**
     * creating OptionPanel function
     * @return OptionsPanel
     */
    private JPanel prepareOptionsPanel(){
        JPanel optionsPanel = new JPanel();
        Sliders sliders = new Sliders(sourceImage);
        opacitySlider = sliders.getOpacitySlider();
        xSlider = sliders.getXSlider();
        ySlider = sliders.getYSlider();

        optionsPanel.add(new JLabel("Width:"));
        optionsPanel.add(xSlider);

        optionsPanel.add(new JLabel("Height:"));
        optionsPanel.add(ySlider);

        optionsPanel.add(new JLabel("Opacity:"));
        optionsPanel.add(opacitySlider);

        return optionsPanel;
    }

    /**
     * creating SubmitButton function
     * @return SubmitButton
     */
    private JButton prepareSubmitButton(){
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            createImageWatermark();
            this.imagePanel.setImage(sourceImage);
            this.dispose();
        });
        return submitButton;
    }

    /**
     * ImageWatermark algorithm
     */
    protected void createImageWatermark(){
        try{

            Graphics2D graphics2D = (Graphics2D) sourceImage.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) opacitySlider.getValue()/100);
            graphics2D.setComposite(alphaChannel);

            int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
            int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;

            graphics2D.drawImage(watermarkImage, (xSlider.getValue()*2-watermarkImage.getHeight())/2,
                    (ySlider.getValue()*2-watermarkImage.getWidth())/2, null);
            graphics2D.dispose();
        }catch (Exception exception){
            com.company.DialogLibrary.showWatermarkProblem();
        }
    }

    /**
     * opening WatermarkImage from file function
     */
    private void getWatermarkImage(){
        JFileChooser fileChooser = new JFileChooser(".\\resources");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG images", "png"));

        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                watermarkImage = ImageIO.read(file);
            } catch (IOException exception) {
                com.company.DialogLibrary.showNoFileDialog();
            }

        }
    }

    /**
     * setting frame properties
     */
    private void setFrame(){
        setTitle("Image Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
