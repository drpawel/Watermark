package com.company;

public class Main {

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        /**
         * linking App View with App Controller
         */
        GUI gui = new GUI();
        AppController appController = new AppController(gui);
        gui.setAppController(appController);
    }
}
