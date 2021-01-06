package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        AppController appController = new AppController(gui);
        gui.setAppController(appController);
    }
}
