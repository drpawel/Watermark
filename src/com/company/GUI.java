package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * class creating App View
 */
public class GUI extends JFrame {
    private JButton openButton,saveButton,textWatermarkButton,imageWatermarkButton;
    private ImagePanel imagePanel;

    /**
     * GUI constructor
     */
    public GUI(){
        this.getContentPane().add(prepareMainPanel());
        setFrame();
    }

    /**
     * preparing Main Panel
     * @return mainPanel
     */
    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600,600));
        mainPanel.add(prepareSelectionPanel(),BorderLayout.PAGE_START);

        this.imagePanel = new ImagePanel();
        mainPanel.add(new JScrollPane(this.imagePanel),BorderLayout.CENTER);
        return mainPanel;
    }

    /**
     * preparing Selection Panel and binding buttons to it
     * @return selectionPanel
     */
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

    /**
     * adding ActionListeners to buttons
     * @param actionListener
     */
    public void setAppController(ActionListener actionListener){
        this.openButton.addActionListener(actionListener);
        this.saveButton.addActionListener(actionListener);
        this.textWatermarkButton.addActionListener(actionListener);
        this.imageWatermarkButton.addActionListener(actionListener);
    }

    /**
     * ImagePanel getter
     * @return imagePanel added to MainPanel
     */
    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    /**
     * setting frame properties
     */
    private void setFrame(){
        setTitle("Watermark Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
