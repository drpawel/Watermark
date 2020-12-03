package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextWatermark extends JFrame {
    private BufferedImage image;
    private JTextField textField = new JTextField(20);
    private Color color = Color.BLUE;
    private String font = "Arial";
    private String text = "PJAVA";

    public TextWatermark(BufferedImage sourceImage){
        this.image = sourceImage;
        if(sourceImage!=null){
            this.getContentPane().add(prepareMainPanel());
            setFrame();
        }
        createTextWatermark();
    }

    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(300,100));
        mainPanel.add(textField,BorderLayout.CENTER);
        mainPanel.add(prepareSubmitButton(),BorderLayout.PAGE_END);
        return mainPanel;
    }

    private JButton prepareSubmitButton(){
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            this.text = this.textField.getText();
            this.dispose();
        });
        return submitButton;
    }

    protected void createTextWatermark(){
        try{
            Graphics2D graphics2D = (Graphics2D) image.getGraphics();

            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            graphics2D.setComposite(alphaComposite);
            graphics2D.setColor(color);
            graphics2D.setFont(new Font(font, Font.BOLD, 32));
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            Rectangle2D rectangle2D = fontMetrics.getStringBounds(text, graphics2D);

            int centerX = (image.getWidth() - (int) rectangle2D.getWidth()) / 2;
            int centerY = image.getHeight() / 2;

            graphics2D.drawString(text, centerX, centerY);
            graphics2D.dispose();
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    private void setFrame(){
        setTitle("Text Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
