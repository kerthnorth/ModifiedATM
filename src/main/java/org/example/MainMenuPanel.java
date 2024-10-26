package org.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenuPanel {

    private JPanel panel;

    public MainMenuPanel(ATMGUI atmGui) {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(50, 86, 120));

        JLabel logoLabel = UIHelper.createLogoLabel();
        JLabel welcomeLabel = UIHelper.createLabel("Welcome To NUMETRO BANK!", 24, Color.WHITE);
        JLabel taglineLabel = UIHelper.createLabel("Your Trusted Partner in Financial Freedom", 16, Color.LIGHT_GRAY);

        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        menuPanel.setBackground(new Color(50, 86, 120));

        JButton loginButton = UIHelper.createButton("1. Login", e -> atmGui.showLoginScreen());
        JButton signupButton = UIHelper.createButton("2. Signup", e -> atmGui.showSignupScreen());
        JButton transactionsButton = UIHelper.createButton("3. Transactions", e -> {
            if (atmGui.getLoggedInCardNumber() == null) {
                JOptionPane.showMessageDialog(atmGui.getFrame(), "You need to log in first!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                atmGui.showTransactionMenu();
            }
        });
        JButton exitButton = UIHelper.createButton("4. Exit", e -> System.exit(0));

        menuPanel.add(loginButton);
        menuPanel.add(signupButton);
        menuPanel.add(transactionsButton);
        menuPanel.add(exitButton);

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonContainer.setBackground(new Color(50, 86, 120));
        buttonContainer.add(menuPanel);

        panel.add(logoLabel, BorderLayout.NORTH);
        panel.add(welcomeLabel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(50, 86, 120));
        southPanel.add(taglineLabel, BorderLayout.NORTH);
        southPanel.add(buttonContainer, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return panel;
    }
}
