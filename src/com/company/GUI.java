package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    private BufferedImage image;

    //GUI constructor
    public GUI(){
        this.getContentPane().add(prepareMainPanel());
        setFrame();
    }

    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.add(prepareSelectionPanel(),BorderLayout.PAGE_START);
        return mainPanel;
    }

    private JPanel prepareSelectionPanel(){
        JPanel selectionPanel = new JPanel();
        selectionPanel.add(prepareOpenButton());
        selectionPanel.add(prepareSaveButton());
        selectionPanel.add(prepareTextWatermarkButton());
        selectionPanel.add(prepareImageWatermarkButton());
        return selectionPanel;
    }

    private JButton prepareOpenButton(){
        JButton openButton = new JButton("Open a File");
        openButton.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser("D:\\IdeaProjects\\Watermark\\resources");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
            fileChooser.addChoosableFileFilter(filter);
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try {
                    image = ImageIO.read(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        });
        return openButton;
    }

    private JButton prepareSaveButton(){
        JButton saveButton = new JButton("Save a File");
        saveButton.addActionListener(e -> {
            System.out.println("SAVE");
        });
        return saveButton;
    }

    private JButton prepareTextWatermarkButton(){
        JButton textWatermarkButton = new JButton("Text Watermark");
        textWatermarkButton.addActionListener(e -> {
            System.out.println("TEXT");
        });
        return textWatermarkButton;
    }

    private JButton prepareImageWatermarkButton(){
        JButton imageWatermarkButton = new JButton("Image Watermark");
        imageWatermarkButton.addActionListener(e -> {
            System.out.println("IMAGE");
        });
        return imageWatermarkButton;
    }
    
    private void setFrame(){
        setTitle("Watermark Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
