package org.example.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    public static final String URL = "jdbc:sqlite:Clientdata.db";

    // Method to create the TRANSACTION_HISTORY table
    public static void createTransactionTable() {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS TRANSACTION_HISTORY (
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            CARDNUMBER varchar(255),
            TRANSACTION_TYPE varchar(255),
            AMOUNT REAL,
            DATE_TIME TEXT
        );
        """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Table 'TRANSACTION_HISTORY' has been created or already exists.");
        } catch (SQLException e) {
            System.out.println("Failed to create the transaction history table.");
            e.printStackTrace();
        }
    }

    // Method to log a transaction (deposit or withdrawal) for a specific user
    public static void logTransaction(String cardNumber, String transactionType, double amount) {
        // Ensure the table exists before logging the transaction
        createTransactionTable();

        String sql = "INSERT INTO TRANSACTION_HISTORY (CARDNUMBER, TRANSACTION_TYPE, AMOUNT, DATE_TIME) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String dateTime = dtf.format(LocalDateTime.now());  // Get the current date and time

            pstmt.setString(1, cardNumber);           // User's card number
            pstmt.setString(2, transactionType);      // Transaction type (Deposit/Withdrawal)
            pstmt.setDouble(3, amount);               // Transaction amount
            pstmt.setString(4, dateTime);             // Date and time of the transaction

            pstmt.executeUpdate();
            System.out.println("Transaction logged: " + transactionType + " of $" + amount + " at " + dateTime);

        } catch (SQLException e) {
            System.out.println("Failed to log the transaction.");
            e.printStackTrace();
        }
    }

    // Method to retrieve transaction history for a specific user
    public static void getTransactionHistory(String cardNumber) {
        String sql = "SELECT TRANSACTION_TYPE, AMOUNT, DATE_TIME FROM TRANSACTION_HISTORY WHERE CARDNUMBER = ? ORDER BY DATE_TIME DESC";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);  // Set the card number parameter

            ResultSet rs = pstmt.executeQuery();

            System.out.println("Transaction History for Card Number: " + cardNumber);
            while (rs.next()) {
                String transactionType = rs.getString("TRANSACTION_TYPE");
                double amount = rs.getDouble("AMOUNT");
                String dateTime = rs.getString("DATE_TIME");

                System.out.println(transactionType + ": $" + amount + " on " + dateTime);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve transaction history.");
            e.printStackTrace();
        }
    }
}
