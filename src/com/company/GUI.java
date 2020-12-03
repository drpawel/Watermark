package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private BufferedImage image;
    private JButton openButton,saveButton,textWatermarkButton,imageWatermarkButton;
    private JLabel imageLabel;

    public GUI(){
        this.getContentPane().add(prepareMainPanel());
        setFrame();
    }

    private JPanel prepareMainPanel(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600,600));
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
        if(imageLabel!=null){
            mainPanel.remove(imageLabel);
        }
        imageLabel = new JLabel(new ImageIcon(this.image));
        mainPanel.add(imageLabel,BorderLayout.CENTER);
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
