package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppController implements ActionListener {
    private final GUI gui;
    private BufferedImage processedImage = null;
    private int counter = 0;

    public AppController(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open a File":
                openFile();
                break;
            case "Save a File":
                saveFile();
                break;
            case "Text Watermark":
                addTextWatermark(gui.getImage());
                break;
            case "Image Watermark":
                addImageWatermark(gui.getImage());
                break;
        }
    }

    private void openFile(){
        JFileChooser fileChooser = new JFileChooser(".\\resources");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG images", "png"));

        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                this.gui.setImage(ImageIO.read(file));
                this.gui.refreshFrame();
            } catch (IOException exception) {
                com.company.DialogLibrary.showNoFileDialog();
            }

        }
    }

    private void saveFile(){
        try {
            ImageIO.write(processedImage, "png", new File(".\\resources\\Watermark_" + counter++
                    +"_"+ new SimpleDateFormat("yyyy-MM-dd-HH-mm'.png'").format(new Date())));
        } catch (Exception exception) {
            com.company.DialogLibrary.showNoProcessedImageDialog();
        }
    }

    private void addTextWatermark(BufferedImage sourceImage){
        TextWatermark textWatermark = new TextWatermark(sourceImage);
        processedImage = textWatermark.getImage();
        if(processedImage!=null){
            this.gui.setImage(processedImage);
            this.gui.refreshFrame();
        }
    }

    private void addImageWatermark(BufferedImage sourceImage){
        ImageWatermark imageWatermark = new ImageWatermark(sourceImage);
        processedImage = imageWatermark.getImage();
        if(processedImage!=null){
            this.gui.setImage(processedImage);
            this.gui.refreshFrame();
        }
    }
}
