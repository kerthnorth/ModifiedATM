package org.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.example.Database.clientdata;

public class LoginScreen {

    private JPanel panel;

    public LoginScreen(ATMGUI atmGui) {
        panel = new JPanel(new BorderLayout());
        panel.add(UIHelper.createLogoLabel(), BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBackground(new Color(50, 86, 120));

        JTextField cardNumberField = new JTextField(15);
        JPasswordField pinField = new JPasswordField(10);
        JButton loginButton = UIHelper.createButton("Login", e -> {
            String cardNumber = cardNumberField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            if (cardNumber.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Card number or PIN cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (clientdata.isValidLogin(cardNumber, pin)) {
                atmGui.setLoggedInCardNumber(cardNumber);
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                atmGui.showTransactionMenu();
            } else {
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Invalid login details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginPanel.add(UIHelper.createLabel("Card Number:", 12, Color.BLACK));
        loginPanel.add(cardNumberField);
        loginPanel.add(UIHelper.createLabel("PIN:", 12, Color.BLACK));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        panel.add(loginPanel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
