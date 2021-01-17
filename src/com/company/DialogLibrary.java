package com.company;

import javax.swing.*;

/**
 * class with MessageDialogs
 */
public class DialogLibrary {

    public static void showNoFileDialog() {
        JOptionPane.showMessageDialog(null,
                "There is no file!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showNoImageDialog() {
        JOptionPane.showMessageDialog(null,
                "There is no image!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showWatermarkProblem() {
        JOptionPane.showMessageDialog(null,
                "There is problem with creating watermark!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }
}
