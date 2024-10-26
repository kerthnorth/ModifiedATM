package org.example;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ATMGUI {

    private String loggedInCardNumber;
    private ATMService atmService;
    private JFrame frame;

    public ATMGUI(ATMService atmService) {
        this.atmService = atmService;
        frame = new JFrame("ATM - NUMETRO BANK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        showMainMenu();
    }

    private void showScreen(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void showMainMenu() {
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        showScreen(mainMenuPanel.getPanel());
    }

    public void showSignupScreen() {
        SignupScreen signupScreen = new SignupScreen(this);
        showScreen(signupScreen.getPanel());
    }

    public void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(this);
        showScreen(loginScreen.getPanel());
    }

    public void showTransactionMenu() {
        TransactionMenu transactionMenu = new TransactionMenu(this, atmService, loggedInCardNumber);
        showScreen(transactionMenu.getPanel());
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setLoggedInCardNumber(String cardNumber) {
        this.loggedInCardNumber = cardNumber;
    }

    public String getLoggedInCardNumber() {
        return loggedInCardNumber;
    }
}
