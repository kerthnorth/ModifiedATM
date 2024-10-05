package org.example;

//package org.example;
import org.example.Database.clientdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {

    private String loggedInCardNumber;
    public ATMService atmService;
    static Transaction deposit = new Transaction();

    public ATMGUI() {
        // Create the frame for the ATM GUI
//        this.atmService = atmService;
        JFrame frame = new JFrame("ATM - NUMETRO BANK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the window

        // Call the method to display the main menu
        showMainMenu(frame);
    }

    // Method to display the main menu
    private void showMainMenu(JFrame frame) {
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

        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen(frame);
            }
        });

        // Add action listener for signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignupScreen(frame); // Show the signup screen
            }
        });
        // Add action listener for transactions button
        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInCardNumber == null) {
                    JOptionPane.showMessageDialog(frame, "You need to log in first!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                showTransactionMenu(frame);
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

        // Add the panel to the frame and make it visible
        frame.getContentPane().removeAll(); // Clear the frame content
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Method to show the signup screen
    private void showSignupScreen(JFrame frame) {
        JPanel signupPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns

        // Create labels and text fields for user information
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel pinLabel = new JLabel("Set a 4-digit PIN:");
        JPasswordField pinField = new JPasswordField();
        JLabel verifyPinLabel = new JLabel("Verify PIN:");
        JPasswordField verifyPinField = new JPasswordField();

        // Create the signup button
        JButton signupButton = new JButton("Signup");

        // Add components to the signup panel
        signupPanel.add(nameLabel);
        signupPanel.add(nameField);
        signupPanel.add(surnameLabel);
        signupPanel.add(surnameField);
        signupPanel.add(emailLabel);
        signupPanel.add(emailField);
        signupPanel.add(pinLabel);
        signupPanel.add(pinField);
        signupPanel.add(verifyPinLabel);
        signupPanel.add(verifyPinField);
        signupPanel.add(new JLabel()); // Placeholder
        signupPanel.add(signupButton);

        // Add action listener to handle signup logic
        signupButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String email = emailField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            String verifyPin = new String(verifyPinField.getPassword()).trim();

            // Perform signup validation and user creation logic
            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || pin.isEmpty() || verifyPin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!pin.equals(verifyPin)) {
                JOptionPane.showMessageDialog(frame, "PINs do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ensure PIN is 4 digits long
            if (pin.length() != 4 || !pin.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "PIN must be exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a User object with input data
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPin(pin);
            // Generate a card number (you can modify this logic as needed)
            String cardNumber = generateCardNumber();
            user.setCardNumber(cardNumber);

            // Add new user to the database
            clientdata.createDatabase(user);  // Assuming this creates the user in the database

            JOptionPane.showMessageDialog(frame, "Signup successful! Your card number is: " + cardNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            showMainMenu(frame); // Return to the main menu
        });

        frame.setContentPane(signupPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Method to generate a new card number (you can implement your own logic)
    private String generateCardNumber() {
        // Generate a random 16-digit card number as a placeholder
        return String.valueOf((long) (Math.random() * 10000000000000000L));
    }

    // Method to show the login screen
// Method to show the login screen
    private void showLoginScreen(JFrame frame) {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns

        // Create labels and text fields for card number and PIN
        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();

        // Create the login button
        JButton loginButton = new JButton("Login");

        // Add components to the login panel
        loginPanel.add(cardNumberLabel);
        loginPanel.add(cardNumberField);
        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(new JLabel()); // Placeholder
        loginPanel.add(loginButton);

        // Add action listener to handle login logic
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText().trim();
                String pin = new String(pinField.getPassword()).trim();

                if (cardNumber.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Card number or PIN cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Validate login using clientdata.isValidLogin
                    boolean isValid = clientdata.isValidLogin(cardNumber, pin);
                    if (isValid) {
                        loggedInCardNumber = cardNumber; // Store logged-in card number
                        JOptionPane.showMessageDialog(frame, "Login successful! Welcome to your account.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Call the transaction menu method after successful login
                        showTransactionMenu(frame);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid login details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Set up the frame content for the login screen
        frame.getContentPane().removeAll(); // Clear the frame content
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showTransactionMenu(JFrame frame) {
        JPanel transactionPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column

        // Create buttons for each transaction type
        JButton viewBalanceButton = new JButton("1. View Balance");
        JButton depositButton = new JButton("2. Deposit");
        JButton withdrawButton = new JButton("3. Withdraw");

        // Style buttons
        viewBalanceButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        depositButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        withdrawButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Add buttons to the panel
        transactionPanel.add(viewBalanceButton);
        transactionPanel.add(depositButton);
        transactionPanel.add(withdrawButton);

        // Add action listeners for each transaction button

        // View balance action
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInCardNumber == null) {
                    JOptionPane.showMessageDialog(frame, "Please log in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double balance = clientdata.getUserBalance(loggedInCardNumber); // Fetch balance from service
                JOptionPane.showMessageDialog(frame, "Your balance is: R" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Deposit action
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInCardNumber == null) {
                    JOptionPane.showMessageDialog(frame, "Please log in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String depositAmountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:", "Deposit", JOptionPane.PLAIN_MESSAGE);
                if (depositAmountStr != null) {
                    try {
                        double depositAmount = Double.parseDouble(depositAmountStr);
                        if (depositAmount <= 0) {
                            JOptionPane.showMessageDialog(frame, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        atmService.deposit(depositAmount); // Call deposit on ATMService
                        double newBalance = clientdata.getUserBalance(loggedInCardNumber);
                        JOptionPane.showMessageDialog(frame, "Deposit successful! Your new balance is: R" + newBalance, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Withdraw action
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInCardNumber == null) {
                    JOptionPane.showMessageDialog(frame, "Please log in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String withdrawAmountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
                if (withdrawAmountStr != null) {
                    try {
                        double withdrawAmount = Double.parseDouble(withdrawAmountStr);
                        if (withdrawAmount <= 0) {
                            JOptionPane.showMessageDialog(frame, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        boolean success = atmService.withdraw(withdrawAmount); // Call withdraw on ATMService
                        if (success) {
                            double newBalance = clientdata.getUserBalance(loggedInCardNumber);
                            JOptionPane.showMessageDialog(frame, "Withdrawal successful! Your new balance is: R" + newBalance, "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add the transaction panel to the frame and display it
        frame.getContentPane().removeAll(); // Clear the frame content
        frame.add(transactionPanel);
        frame.revalidate();
        frame.repaint();
    }



}
