package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame {
    private BufferedImage image;
    private JButton openButton,saveButton,textWatermarkButton,imageWatermarkButton;
    private JPanel mainPanel;

    public GUI(){
        this.getContentPane().add(prepareMainPanel());
        setFrame();
    }

    private JPanel prepareMainPanel(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.add(prepareSelectionPanel(),BorderLayout.PAGE_START);
        return mainPanel;
    }

    private JPanel prepareSelectionPanel(){
        JPanel selectionPanel = new JPanel();

        openButton = new JButton("Open a File");
        saveButton = new JButton("Save a File");
        textWatermarkButton = new JButton("Text Watermark");
        imageWatermarkButton = new JButton("Image Watermark");

        selectionPanel.add(openButton);
        selectionPanel.add(saveButton);
        selectionPanel.add(textWatermarkButton);
        selectionPanel.add(imageWatermarkButton);
        return selectionPanel;
    }

    protected void setAppController(ActionListener actionListener){
        this.openButton.addActionListener(actionListener);
        this.saveButton.addActionListener(actionListener);
        this.textWatermarkButton.addActionListener(actionListener);
        this.imageWatermarkButton.addActionListener(actionListener);
    }

    protected void setImage(BufferedImage image){
        this.image = image;
    }

    protected BufferedImage getImage(){
        return this.image;
    }

    protected void refreshFrame(){
        mainPanel.add(new JLabel(new ImageIcon(this.image)),BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void setFrame(){
        setTitle("Watermark Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
