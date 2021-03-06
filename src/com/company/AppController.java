package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * App controller
 */
public class AppController implements ActionListener {
    private final GUI gui;
    private ImagePanel imagePanel;
    private int counter = 0;

    /**
     * App controller constructor
     * @param gui
     */
    public AppController(GUI gui) {
        this.gui = gui;
        this.imagePanel = this.gui.getImagePanel();
    }

    /**
     * function with actionListeners
     * @param e
     */
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
                addTextWatermark(this.imagePanel);
                break;
            case "Image Watermark":
                addImageWatermark(this.imagePanel);
                break;
        }
    }

    /**
     * opening file function
     */
    private void openFile(){
        JFileChooser fileChooser = new JFileChooser(".\\resources");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG images", "png"));

        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                this.imagePanel.setImage(ImageIO.read(file));
            } catch (IOException exception) {
                com.company.DialogLibrary.showNoFileDialog();
            }

        }
    }

    /**
     * saving file function
     */
    private void saveFile(){
        try {
            ImageIO.write(this.imagePanel.getImage(), "png", new File(".\\resources\\Watermark_" + counter++
                    +"_"+ new SimpleDateFormat("yyyy-MM-dd-HH-mm'.png'").format(new Date())));
        } catch (Exception exception) {
            com.company.DialogLibrary.showNoImageDialog();
        }
    }

    /**
     * adding TextWatermark
     * @param panel
     */
    private void addTextWatermark(ImagePanel panel){
        new TextWatermark(panel);
    }

    /**
     * adding ImageWatermark
     * @param panel
     */
    private void addImageWatermark(ImagePanel panel){
        new ImageWatermark(panel);
    }
}
