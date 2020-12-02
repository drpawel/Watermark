package com.company;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class AbstractWatermark extends JFrame {
    BufferedImage sourceImage;

    public AbstractWatermark(BufferedImage sourceImage){
        this.sourceImage = sourceImage;
    }

    public BufferedImage getImage() {
        return sourceImage;
    }

    void setFrame(String name){
        setTitle(name);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
