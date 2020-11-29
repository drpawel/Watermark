package com.company;

import javax.swing.*;

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
}
