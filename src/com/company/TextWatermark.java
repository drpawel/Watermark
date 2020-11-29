package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextWatermark extends JFrame {


    public TextWatermark(BufferedImage image){
        this.getContentPane().add(prepareTextWatermarkPanel());
        createTextWatermark(image,Color.BLUE,"Arial","XDD");
        setFrame();

    }

    private JPanel prepareTextWatermarkPanel(){
        JPanel textWatermarkPanel = new JPanel(new BorderLayout());

        return textWatermarkPanel;
    }

    private void createTextWatermark(BufferedImage image,Color color,String font, String text){
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
        try {
            ImageIO.write(image, "png", new File("D:\\IdeaProjects\\Watermark\\resources\\textWatermark"+System.currentTimeMillis()+".png"));
        } catch (IOException exception) {
            com.company.DialogLibrary.showNoImageDialog();
        }
        graphics2D.dispose();
    }

    private void setFrame(){
        setTitle("Text Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
