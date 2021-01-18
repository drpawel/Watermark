package com.company;

import javax.swing.*;

/**
 * class with MessageDialogs
 */
public class DialogLibrary {

    /**
     * MessageDialog - "There is no file!"
     */
    public static void showNoFileDialog() {
        JOptionPane.showMessageDialog(null,
                "There is no file!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * MessageDialog - "There is no image!"
     */
    public static void showNoImageDialog() {
        JOptionPane.showMessageDialog(null,
                "There is no image!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * MessageDialog - "There is problem with creating watermark!"
     */
    public static void showWatermarkProblem() {
        JOptionPane.showMessageDialog(null,
                "There is problem with creating watermark!",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }
}
