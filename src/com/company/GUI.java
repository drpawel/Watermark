package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JButton openButton,saveButton,textWatermarkButton,imageWatermarkButton;
    private ImagePanel imagePanel;

    public GUI(){
        this.getContentPane().add(prepareMainPanel());
        setFrame();
    }

    private JPanel prepareMainPanel(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600,600));
        mainPanel.add(prepareSelectionPanel(),BorderLayout.PAGE_START);

        this.imagePanel = new ImagePanel();
        mainPanel.add(new JScrollPane(this.imagePanel),BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel prepareSelectionPanel(){
        JPanel selectionPanel = new JPanel();
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

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

    public void setAppController(ActionListener actionListener){
        this.openButton.addActionListener(actionListener);
        this.saveButton.addActionListener(actionListener);
        this.textWatermarkButton.addActionListener(actionListener);
        this.imageWatermarkButton.addActionListener(actionListener);
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    private void setFrame(){
        setTitle("Watermark Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
