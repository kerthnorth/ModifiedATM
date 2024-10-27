package org.example;

import java.util.Random;

public class User {

    public String name;
    public String surname;
    public String email;
    public String cardNumber;
    public String pin;
    public boolean balance;
    public String transactionHistory;
    public String verifypin;
    int cardNumberLength = 9;

    public User() {
        this.cardNumber = generateCardNumber(cardNumberLength);
    }

    public String generateCardNumber(int length) {
        Random random = new Random();
        StringBuilder cardnumber = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            cardnumber.append(digit);
        }

        return cardnumber.toString();
    }

    public String getVerifypin() {
        return verifypin;
    }

    public void setVerifypin(String verifypin) {
        this.verifypin = verifypin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setTransactionHistory(String transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDetails() {
        return String.format("Name: %s\nSurname: %s\nEmail: %s\nPIN: %s\nCard Number: %s",
                name, surname, email, pin, cardNumber);
    }
}
