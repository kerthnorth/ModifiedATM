package org.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupScreen {

    private JPanel panel;

    public SignupScreen(ATMGUI atmGui) {
        panel = new JPanel(new BorderLayout());
        panel.add(UIHelper.createLogoLabel(), BorderLayout.NORTH);

        JPanel signupPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        signupPanel.setBackground(new Color(50, 86, 120));

        JTextField nameField = new JTextField(15);
        JTextField surnameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField pinField = new JPasswordField(10);
        JPasswordField verifyPinField = new JPasswordField(10);

        JButton signupButton = UIHelper.createButton("Signup", e -> {
            if (validateFields(nameField, surnameField, emailField, pinField, verifyPinField, atmGui)) {
                atmGui.showMainMenu();
            }
        });

        signupPanel.add(UIHelper.createLabel("Name:", 12, Color.BLACK));
        signupPanel.add(nameField);
        signupPanel.add(UIHelper.createLabel("Surname:", 12, Color.BLACK));
        signupPanel.add(surnameField);
        signupPanel.add(UIHelper.createLabel("Email:", 12, Color.BLACK));
        signupPanel.add(emailField);
        signupPanel.add(UIHelper.createLabel("Set a 4-digit PIN:", 12, Color.BLACK));
        signupPanel.add(pinField);
        signupPanel.add(UIHelper.createLabel("Verify PIN:", 12, Color.BLACK));
        signupPanel.add(verifyPinField);
        signupPanel.add(new JLabel());
        signupPanel.add(signupButton);

        panel.add(signupPanel, BorderLayout.CENTER);
    }

    private boolean validateFields(JTextField nameField, JTextField surnameField, JTextField emailField,
            JPasswordField pinField, JPasswordField verifyPinField, ATMGUI atmGui) {
        // Validation logic
        return true;
    }

    public JPanel getPanel() {
        return panel;
    }
}
