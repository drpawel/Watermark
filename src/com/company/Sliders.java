package com.company;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Sliders {
    private final JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL,0,100,30);
    private JSlider xSlider, ySlider;

    public Sliders(BufferedImage image) {
        opacitySlider.setPaintTrack(true);
        opacitySlider.setPaintTicks(true);
        opacitySlider.setPaintLabels(true);
        opacitySlider.setMajorTickSpacing(50);
        opacitySlider.setMinorTickSpacing(5);

        xSlider = new JSlider(JSlider.HORIZONTAL,0,image.getWidth(),image.getWidth()/2);
        xSlider.setPaintTrack(true);
        xSlider.setPaintTicks(true);
        xSlider.setPaintLabels(true);
        xSlider.setMajorTickSpacing(100);
        xSlider.setMinorTickSpacing(20);

        ySlider = new JSlider(JSlider.HORIZONTAL,0,image.getHeight(),image.getHeight()/2);
        ySlider.setPaintTrack(true);
        ySlider.setPaintTicks(true);
        ySlider.setPaintLabels(true);
        ySlider.setMajorTickSpacing(100);
        ySlider.setMinorTickSpacing(20);
    }

    public JSlider getXSlider() {
        return xSlider;
    }

    public JSlider getYSlider() {
        return ySlider;
    }

    public JSlider getOpacitySlider() {
        return opacitySlider;
    }
}
