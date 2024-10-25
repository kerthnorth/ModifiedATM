package org.example;

import javax.swing.SwingUtilities;

public class ATM {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new ATMGUI(null);
            }
        });
    }

    static void showMenu() {
        ATMMenu atmMenu = new ATMMenu();
        atmMenu.getMainMenu();
    }

    static void executeOption(int choice) {
        // Logic for executing options can be added here if needed for terminal
    }
}
