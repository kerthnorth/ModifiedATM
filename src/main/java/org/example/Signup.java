package org.example;

import java.util.Scanner;

public class Signup {

    static Scanner sc = new Scanner(System.in);
    static User b = new User();

    public User start() {
        System.out.println("Welcome to the Banking system");
        String cardNumber;
        String verifypin;
        User user = new User();
        user.getCardNumber();

        while (true) {
            System.out.println("Enter your name:");
            String name = sc.nextLine().trim();

            if (!name.isEmpty()) {
                user.setName(name);
                break;
            } else {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your surname:");
            String surname = sc.nextLine().trim();

            if (!surname.isEmpty()) {
                user.setSurname(surname);
                break;
            } else {
                System.out.println("Surname cannot be empty. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your email address:");
            String email = sc.nextLine().trim();

            if (email.contains("@")) {
                user.setEmail(email);
                break;
            } else {
                System.out.println("Invalid email. Please try again.");
            }
        }

        String pin;
        while (true) {
            System.out.println("Set a 4-digit pin number: ");
            pin = sc.nextLine();

            try {
                if (pin.length() == 4) {
                    user.setPin(pin);
                    // b.setPin(Integer.parseInt(pin));

                    break;
                } else {
                    System.out.println("Please set a valid 4-digit pin.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric pin.");
            }
        }

        System.out.println("Please re-enter your pin for verification: ");
        verifypin = sc.nextLine();
        // pin

        while (!verifypin.equals(pin)) {
            System.out.println("Your pin did not match. Please try again");

            verifypin = sc.nextLine();
            if (verifypin.equals(pin)) {
                System.out.println("Welcome To Numetro Bank" + " " + user.name + " " + user.surname + "!");
                System.out.println("Your card number is:" + user.getCardNumber());

                // b.setVerifypin(Integer.parseInt(verifypin));
//                System.out.println("Welcome To Numetro Bank"+ " " +user.name +" "+ user.surname + "!");
//                System.out.println("Your card number is:" + user.getCardNumber());
                break;
            }
        }
        return user;
    }
}
