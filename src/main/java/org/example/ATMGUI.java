package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {

    public ATMGUI() {
        // Create frame for the ATM GUI
        JFrame frame = new JFrame("ATM - NUMETRO BANK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the window

        // Create the main panel with a BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50)); // Dark background for a modern look

        // Create and style the welcome label
        JLabel welcomeLabel = new JLabel("Welcome To NUMETRO BANK!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Bold, elegant font
        welcomeLabel.setForeground(Color.WHITE); // White text for contrast
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Padding

        // Add a tagline below the main welcome message
        JLabel taglineLabel = new JLabel("Your Trusted Partner in Financial Freedom", SwingConstants.CENTER);
        taglineLabel.setFont(new Font("SansSerif", Font.ITALIC, 16)); // Softer italic for tagline
        taglineLabel.setForeground(Color.LIGHT_GRAY); // Subtle color for tagline

        // Create a panel for the main menu buttons
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, with gaps between buttons
        menuPanel.setBackground(new Color(50, 50, 50)); // Same background color

        // Add buttons for each menu option
        JButton loginButton = new JButton("1. Login");
        JButton signupButton = new JButton("2. Signup");
        JButton transactionsButton = new JButton("3. Transactions");
        JButton exitButton = new JButton("4. Exit");

        // Style buttons
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        transactionsButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Add buttons to the menu panel
        menuPanel.add(loginButton);
        menuPanel.add(signupButton);
        menuPanel.add(transactionsButton);
        menuPanel.add(exitButton);

        // Add action listeners to handle button clicks
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You selected Login");
                // Call the login method logic here (to be implemented later)
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You selected Signup");
                // Call the signup method logic here (to be implemented later)
            }
        });

        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You selected Transactions");
                // Call the transactions menu logic here (to be implemented later)
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Exiting the program. Goodbye!");
                System.exit(0); // Exit the application
            }
        });

        // Add components to the main panel
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(taglineLabel, BorderLayout.CENTER);
        panel.add(menuPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
