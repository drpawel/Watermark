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
                addWatermark(gui.getImage());
                break;
            case "Image Watermark":
                System.out.println("IMAGE");
                break;
        }
    }

    private void openFile(){
        JFileChooser fileChooser = new JFileChooser("D:\\IdeaProjects\\Watermark\\resources");
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
            ImageIO.write(processedImage, "png", new File("D:\\IdeaProjects\\Watermark\\resources\\textWatermark_"
                    + counter++ +"_"+ new SimpleDateFormat("yyyy-MM-dd-HH-mm'.png'").format(new Date())));
        } catch (Exception exception) {
            com.company.DialogLibrary.showNoProcessedImageDialog();
        }
    }

    private void addWatermark(BufferedImage sourceImage){
        try{
            TextWatermark textWatermark = new TextWatermark(sourceImage);
            processedImage = textWatermark.getImage();
            this.gui.setImage(processedImage);
            this.gui.refreshFrame();
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }
}
