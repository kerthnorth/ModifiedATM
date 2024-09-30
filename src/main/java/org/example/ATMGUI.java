package org.example;

import javax.swing.*;
import java.awt.*;

public class ATMGUI {

    // Constructor to initialize the GUI
    public ATMGUI() {
        // Create frame for the ATM GUI
        JFrame frame = new JFrame("ATM - NUMETRO BANK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        // Create the welcome panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50)); // Dark background for a modern look

        // Create and style the welcome label
        JLabel welcomeLabel = new JLabel("Welcome To NUMETRO BANK!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Bold, elegant font
        welcomeLabel.setForeground(Color.WHITE); // White text for contrast
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10)); // Padding

        // Add a tagline below the main welcome message
        JLabel taglineLabel = new JLabel("Your Trusted Partner in Financial Freedom", SwingConstants.CENTER);
        taglineLabel.setFont(new Font("SansSerif", Font.ITALIC, 16)); // Softer italic for tagline
        taglineLabel.setForeground(Color.LIGHT_GRAY); // Subtle color for tagline

        // Add both labels to the panel
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(taglineLabel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
