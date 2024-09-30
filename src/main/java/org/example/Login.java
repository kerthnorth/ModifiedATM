////package org.example;
////
////import org.example.Database.clientdata;
////
////import java.util.Scanner;
////
////public class Login {
////    static Scanner sc = new Scanner(System.in);
////
////    public void Signin() {
////        System.out.println("Welcome to the Banking system");
////        System.out.println("Please enter the following details:");
////
////        while (true) {
////            System.out.println("Enter your card number:");
////            String cardNumber = sc.nextLine().trim();
////            System.out.println("Enter your pin:");
////            String pin = sc.nextLine().trim();
////            boolean isValid = clientdata.isValidLogin(cardNumber, pin);
////
////            if (isValid) {
////                double balance = clientdata.getUserBalance(cardNumber);
////                System.out.println("Login successful! Welcome to your account.");
////                System.out.println("Your current balance is: $" + balance);
////                break;
////            } else {
////                System.out.println("Invalid login details. Please try again.");
////            }
////        }
////    }
////}
//package org.example;
//
//import org.example.Database.clientdata;
//
//import java.util.Scanner;
//
//public class Login {
//    ATMMenu menu = new ATMMenu();
//    private String loggedInCardNumber;  // Store the logged-in card number
//    static Scanner sc = new Scanner(System.in);
//
//    // Method to handle user login
//    public void Signin() {
//        System.out.println("Welcome to the Banking system");
//        System.out.println("Please enter the following details:");
//
//        while (true) {
//            // Prompt the user to enter their card number and pin
//            System.out.println("Enter your card number:");
//            String cardNumber = sc.nextLine().trim();
//            System.out.println("Enter your pin:");
//            String pin = sc.nextLine().trim();
//
//            // Check if the card number and pin are valid using the database
//            boolean isValid = clientdata.isValidLogin(cardNumber, pin);
//
//            if (isValid) {
//                // If login is successful, store the logged-in card number
//                loggedInCardNumber = cardNumber;
//                System.out.println("Login successful! Welcome to your account.");
//                menu.getTransactionMenu();
//
//                break;
//            } else {
//                // If login fails, prompt the user again
//                System.out.println("Invalid login details. Please try again.");
//            }
//        }
//    }
//
//    // Check if login was successful
//    public boolean isLoggedIn() {
//        return loggedInCardNumber != null;
//    }
//
//    // Get the logged-in card number
//    public String getLoggedInCardNumber() {
//        return loggedInCardNumber;
//    }
//}
//
package org.example;

import org.example.Database.clientdata;

import java.util.Scanner;

public class Login {
    private String loggedInCardNumber;  // Store the logged-in card number
    static Scanner sc = new Scanner(System.in);

//     Method to handle user login
    public boolean Signin() {
        System.out.println("Welcome to the Banking system");
        System.out.println("Please enter the following details:");

        while (true) {
            // Prompt the user to enter their card number and pin
            System.out.println("Enter your card number:");
            String cardNumber = sc.nextLine().trim();
            System.out.println("Enter your pin:");
            String pin = sc.nextLine().trim();

            // Check if the card number and pin are valid using the database
            boolean isValid = clientdata.isValidLogin(cardNumber, pin);

            if (isValid) {
                // If login is successful, store the logged-in card number
                loggedInCardNumber = cardNumber;
                System.out.println("Login successful! Welcome to your account.");
                break;
            } else {
                // If login fails, prompt the user again
                System.out.println("Invalid login details. Please try again.");
            }
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedInCardNumber != null;
    }

    // Get the logged-in card number
    public String getLoggedInCardNumber() {
        return loggedInCardNumber;
    }
}
