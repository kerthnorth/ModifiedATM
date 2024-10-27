package org.example;

import java.util.Scanner;

import org.example.Database.clientdata;

public class ATMMenu {

    private ATMService atmService; // Initialize this later after login
    private boolean isLoggedIn = false;
    private String loggedInCardNumber = null;
    static Transaction deposit = new Transaction();// Store the card number of the logged-in user

    // Main menu display
    public static void displayMainMenu() {
        System.out.println("Date: " + deposit.getDate());
        System.out.println("=== Main Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Transactions");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Transaction menu display
    public static void displayTransactionMenu() {
        System.out.println("Date: " + deposit.getDate());
        System.out.println("=== Transaction Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transaction History");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    // Main menu logic
    public void getMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMainMenu();
            int choice = getUserInput(scanner);
            switch (choice) {
                case 1:
                    // Login logic
                    System.out.println("You selected Option 1: Login");
                    Login login = new Login();
                    login.Signin();  // Attempt login

                    // Check if login is successful and get the card number
                    if (login.isLoggedIn()) {
                        this.isLoggedIn = true;
                        this.loggedInCardNumber = login.getLoggedInCardNumber();
                        this.atmService = new ATMService(loggedInCardNumber); // Initialize ATMService with the logged-in card number
                    }
                    break;
                case 2:
                    // Signup logic
                    System.out.println("You selected Option 2: Signup");
                    Signup signup = new Signup();
                    User user = signup.start();
//                    double initialBalance = atmService.getBalance();
                    System.out.println("Welcome To Numetro Bank, " + user.name + " " + user.surname + "!\nYour current balance is: ");
                    System.out.println("Your card number is: " + user.getCardNumber());
                    clientdata.createDatabase(user);  // Add new user to the database
                    break;
                case 3:
                    // Transaction menu logic - check if logged in
                    if (isLoggedIn) {
                        getTransactionMenu();  // User can only access the transaction menu if logged in
                    } else {
                        System.out.println("Please log in first to access transactions.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Transaction menu logic
    public void getTransactionMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayTransactionMenu();
            int choice = getUserInput(scanner);
            switch (choice) {
                case 1:
                    // Deposit logic
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atmService.deposit(depositAmount, loggedInCardNumber);  // Deposit to the logged-in account
                    System.out.println("Deposit successful! Your new balance is: " + clientdata.getUserBalance(loggedInCardNumber));
                    break;
                case 2:
                    // Withdraw logic
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (atmService.withdraw(withdrawAmount, loggedInCardNumber)) {
                        System.out.println("Withdrawal successful! Your new balance is: " + clientdata.getUserBalance(loggedInCardNumber));
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 3:
                    // Check balance logic
                    double balance = clientdata.getUserBalance(loggedInCardNumber);
                    System.out.println("Your current balance is: $" + balance);
                    break;
                case 4:
                    
                    System.out.println("Transaction History:\n" + atmService.getTransactionDetails());
                    break;
                case 5:

                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Get user input (handling invalid inputs)
    public int getUserInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
