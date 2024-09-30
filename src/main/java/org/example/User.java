package org.example;

import java.util.Random;

public class User {
    public String name;
    public String surname;
    public String email;
    public int cardNumber; // Uncommented
    public String pin;
    public boolean balance;
    public String transactionHistory;
    public String verifypin;
    int cardNumberLength = 9;

    // Generate the card number when the User object is created
    public User() {
        this.cardNumber = Integer.parseInt(generateCardNumber(cardNumberLength));
    }

    // Generate a random card number
    public String generateCardNumber(int length) {
        Random random = new Random();
        StringBuilder cardnumber = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generates a random digit between 0 and 9
            cardnumber.append(digit);
        }

        return cardnumber.toString();
    }

    // Getter and setter methods
    public String getVerifypin() {
        return verifypin;
    }

    public void setVerifypin(String verifypin) {
        this.verifypin = verifypin;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean getBalance() {
        return balance;
    }

    public void setBalance(boolean balance) {
        this.balance = balance;
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    public void setTransactionHistory(String transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname=surname;
    }

    public void setEmail(String email) {
        this.email=email;
    }
    public String getUserDetails() {
        return String.format("Name: %s\nSurname: %s\nEmail: %s\nPIN: %d\nCard Number: %d",
                name, surname, email, pin, cardNumber);
    }
}
