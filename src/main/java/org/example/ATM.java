package org.example;

import javax.swing.*;

public class ATM {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI();  // Initialize the ATM GUI
            }
        });
    }



    // Optional method to display the terminal version's menu (if needed)
    static void showMenu() {
        ATMMenu atmMenu = new ATMMenu();
        atmMenu.getMainMenu();  // Call the main menu logic
    }

    // Optional method to execute options, if you want a terminal menu logic
    static void executeOption(int choice) {
        // Logic for executing options can be added here if needed for terminal
    }
}
