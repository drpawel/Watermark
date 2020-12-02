package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWatermark extends JFrame {
    BufferedImage sourceImage;
    BufferedImage watermarkImage;


    public ImageWatermark(BufferedImage sourceImage){
        this.sourceImage = sourceImage;
        getWatermarkImage();
        this.getContentPane().add(prepareImageWatermarkPanel());
        createImageWatermark();
        setFrame();
    }

    private JPanel prepareImageWatermarkPanel(){
        JPanel imageWatermarkPanel = new JPanel(new BorderLayout());
        imageWatermarkPanel.setPreferredSize(new Dimension(300,300));
        //imageWatermarkPanel.add(new JLabel(new ImageIcon(sourceImage)),BorderLayout.CENTER);
        return imageWatermarkPanel;
    }

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

    protected void createImageWatermark(){
        try{
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            g2d.setComposite(alphaChannel);

            int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
            int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;

            g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);
            g2d.dispose();
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }

    public BufferedImage getImage() {
        return sourceImage;
    }

    private void setFrame(){
        setTitle("Image Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
