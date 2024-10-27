package org.example;

import org.example.Database.TransactionHistory;
import org.example.Database.clientdata;

public class ATMService {

    private double balance;
    private StringBuilder transactionHistory = new StringBuilder();
    private String cardNumber;

    public ATMService(String cardNumber) {
        this.cardNumber = cardNumber;
        this.balance = clientdata.getUserBalance(cardNumber);
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionDetails() {
        return transactionHistory.toString();
    }

    public void deposit(double amount, String cardNumber) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposited: ").append(amount).append("\n");
            System.out.println("Successfully deposited: " + amount);
            clientdata.updateUserBalance(cardNumber, balance);
            System.out.println("Successfully deposited: " + balance + " to " + cardNumber);
            transactionHistory.append("Deposited: ").append(amount).append("\n");
            TransactionHistory.logTransaction(cardNumber, "Deposit", amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public boolean withdraw(double amount, String cardNumber) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrew: ").append(amount).append("\n");
            System.out.println("Successfully withdrew: " + amount);
            clientdata.updateUserBalance(cardNumber, balance);
            TransactionHistory.logTransaction(cardNumber, "Withdrawal", amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
        return false;
    }

    public void checkBalance() {
        System.out.println("Your current balance is: " + balance);
    }

    public void showTransactionHistory() {
        TransactionHistory.getTransactionHistory(cardNumber);
    }
}
