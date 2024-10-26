package org.example;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.example.Database.clientdata;

public class TransactionMenu {

    private JPanel panel;

    public TransactionMenu(ATMGUI atmGui, ATMService atmService, String cardNumber) {
        panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton viewBalanceButton = UIHelper.createButton("View Balance", e -> {
            double balance = clientdata.getUserBalance(cardNumber);
            JOptionPane.showMessageDialog(atmGui.getFrame(), "Your balance is: R" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton depositButton = UIHelper.createButton("Deposit", e -> {
            String depositAmountStr = JOptionPane.showInputDialog(atmGui.getFrame(), "Enter amount to deposit:");
            try {
                double depositAmount = Double.parseDouble(depositAmountStr);
                atmService.deposit(depositAmount, cardNumber);
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton withdrawButton = UIHelper.createButton("Withdraw", e -> {
            String withdrawAmountStr = JOptionPane.showInputDialog(atmGui.getFrame(), "Enter amount to withdraw:");
            try {
                double withdrawAmount = Double.parseDouble(withdrawAmountStr);
                if (atmService.withdraw(withdrawAmount, cardNumber)) {
                    JOptionPane.showMessageDialog(atmGui.getFrame(), "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(atmGui.getFrame(), "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(atmGui.getFrame(), "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(viewBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
    }

    public JPanel getPanel() {
        return panel;
    }
}
