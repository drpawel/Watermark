package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextWatermark extends JFrame {
    private BufferedImage image;
    private ImagePanel imagePanel;
    private JTextField textField = new JTextField(10);
    private JColorChooser colorChooser = new JColorChooser();
    private Color color = Color.white;
    private String font = "Arial";
    private String text = "DEFAULT";

    public TextWatermark(ImagePanel imagePanel){
        this.imagePanel = imagePanel;
        this.image = imagePanel.getImage();
        if(imagePanel.getImage()!=null){
            this.getContentPane().add(prepareMainPanel());
            setFrame();
        }else{
            com.company.DialogLibrary.showNoImageDialog();
        }
    }

    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,400));
        mainPanel.add(colorChooser,BorderLayout.PAGE_START);
        mainPanel.add(textField,BorderLayout.CENTER);
        mainPanel.add(prepareSubmitButton(),BorderLayout.PAGE_END);
        return mainPanel;
    }

    private JButton prepareSubmitButton(){
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if(this.textField.getText()!=null){
                this.text = this.textField.getText();
            }
            this.color = colorChooser.getColor();

            createTextWatermark();
            this.imagePanel.setImage(image);
            this.dispose();
        });
        return submitButton;
    }

    public void createTextWatermark(){
        try{
            Graphics2D graphics2D = (Graphics2D) image.getGraphics();

            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            graphics2D.setComposite(alphaComposite);
            graphics2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
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

    private void setFrame(){
        setTitle("Text Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
