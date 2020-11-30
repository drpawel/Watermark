package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextWatermark extends JFrame {
    BufferedImage image;
    Color color = Color.BLUE;
    String font = "Arial";
    String text = "XDDDD";

    public TextWatermark(BufferedImage sourceImage){
        this.image = sourceImage;
        this.getContentPane().add(prepareTextWatermarkPanel());
        createTextWatermark();
        setFrame();
    }

    private JPanel prepareTextWatermarkPanel(){
        JPanel textWatermarkPanel = new JPanel(new BorderLayout());
        textWatermarkPanel.setPreferredSize(new Dimension(image.getWidth()*2,image.getHeight()*2));
        //textWatermarkPanel.add(new JLabel(new ImageIcon(image)),BorderLayout.CENTER);
        return textWatermarkPanel;
    }

    protected void createTextWatermark(){
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();

        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        graphics2D.setComposite(alphaComposite);
        graphics2D.setColor(color);
        graphics2D.setFont(new Font(font, Font.BOLD, 32));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        Rectangle2D rectangle2D = fontMetrics.getStringBounds(text, graphics2D);

        int centerX = (image.getWidth() - (int) rectangle2D.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        graphics2D.drawString("XDDD", centerX, centerY);
        graphics2D.dispose();
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
