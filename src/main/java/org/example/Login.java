package org.example;

import java.util.Scanner;

import org.example.Database.clientdata;

public class Login {

    private String loggedInCardNumber;
    static Scanner sc = new Scanner(System.in);

    public boolean Signin() {
        System.out.println("Welcome to the Banking system");
        System.out.println("Please enter the following details:");

        while (true) {
            System.out.println("Enter your card number:");
            String cardNumber = sc.nextLine().trim();
            System.out.println("Enter your pin:");
            String pin = sc.nextLine().trim();
            boolean isValid = clientdata.isValidLogin(cardNumber, pin);

            if (isValid) {

                loggedInCardNumber = cardNumber;
                System.out.println("Login successful! Welcome to your account.");
                break;
            } else {
                System.out.println("Invalid login details. Please try again.");
            }
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedInCardNumber != null;
    }

    public String getLoggedInCardNumber() {
        return loggedInCardNumber;
    }
}
