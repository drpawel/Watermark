package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AppController implements ActionListener {
    private GUI gui = null;

    public AppController(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Open a File")){
            openFile();
        }else if(e.getActionCommand().equals("Save a File")){
            System.out.println("SAVE");
        }else if(e.getActionCommand().equals("Text Watermark")){
            addWatermark(gui.getImage());
        }else if(e.getActionCommand().equals("Image Watermark")){
            System.out.println("IMAGE");
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
            } catch (IOException exception) {
                com.company.DialogLibrary.showNoFileDialog();
            }

        }
    }

    private void saveFile(){
    }

    private void addWatermark(BufferedImage image){
        try{
            new TextWatermark(image);
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }
}
