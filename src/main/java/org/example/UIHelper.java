package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UIHelper {

    public static JLabel createLogoLabel() {
        ImageIcon logoIcon;
        java.net.URL logoUrl = UIHelper.class.getResource("/org/example/numetro_logo.png");

        if (logoUrl != null) {
            logoIcon = new ImageIcon(logoUrl);
            Image logoImage = logoIcon.getImage();
            Image scaledLogoImage = logoImage.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(scaledLogoImage);
        } else {
            logoIcon = new ImageIcon();
            System.err.println("Warning: Logo image file not found in package 'org.example'");
        }

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Match the background color with the LoginScreen
        logoLabel.setOpaque(true);
        logoLabel.setBackground(new Color(50, 86, 120));

        if (logoIcon.getIconWidth() == -1) {
            logoLabel.setText("Logo unavailable");
            logoLabel.setForeground(Color.RED);
            logoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        }
        return logoLabel;
    }

    public static JLabel createLabel(String text, int fontSize, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(color);
        return label;
    }

    public static JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(180, 40));
        button.addActionListener(action);
        return button;
    }
}
