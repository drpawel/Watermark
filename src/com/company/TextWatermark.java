package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextWatermark extends AbstractWatermark {
    Color color = Color.BLUE;
    String font = "Arial";
    String text = "Watermark";

    public TextWatermark(BufferedImage sourceImage){
        super(sourceImage);
        this.getContentPane().add(prepareTextWatermarkPanel());
        createTextWatermark();
        setFrame("Text Watermark");
    }

    private JPanel prepareTextWatermarkPanel(){
        JPanel textWatermarkPanel = new JPanel(new BorderLayout());
        textWatermarkPanel.setPreferredSize(new Dimension(300,300));

        return textWatermarkPanel;
    }

    protected void createTextWatermark(){
        try{
            Graphics2D graphics2D = (Graphics2D) sourceImage.getGraphics();

            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            graphics2D.setComposite(alphaComposite);
            graphics2D.setColor(color);
            graphics2D.setFont(new Font(font, Font.BOLD, 32));
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            Rectangle2D rectangle2D = fontMetrics.getStringBounds(text, graphics2D);

            int centerX = (sourceImage.getWidth() - (int) rectangle2D.getWidth()) / 2;
            int centerY = sourceImage.getHeight() / 2;

            graphics2D.drawString("XDDD", centerX, centerY);
            graphics2D.dispose();
        }catch (Exception exception){
            com.company.DialogLibrary.showNoImageDialog();
        }
    }
}
