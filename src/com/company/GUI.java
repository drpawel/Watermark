package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JFileChooser fileChooser;

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
            System.out.println("OPEN");
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
