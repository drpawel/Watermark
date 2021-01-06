package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWatermark extends JFrame{
    private ImagePanel imagePanel;
    private BufferedImage sourceImage;
    private BufferedImage watermarkImage = null;


    public ImageWatermark(ImagePanel imagePanel){
        this.imagePanel = imagePanel;
        this.sourceImage = imagePanel.getImage();
        createImageWatermark();
    }

    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,400));
        return mainPanel;
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
            getWatermarkImage();
            Graphics2D graphics2D = (Graphics2D) sourceImage.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            graphics2D.setComposite(alphaChannel);

            int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
            int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;

            graphics2D.drawImage(watermarkImage, topLeftX, topLeftY, null);
            graphics2D.dispose();

            this.imagePanel.setImage(sourceImage);
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }
}
