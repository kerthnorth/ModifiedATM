package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        try (Connection connection = DriverManager.getConnection(URL); Statement stmt = connection.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Table 'TRANSACTION_HISTORY' has been created or already exists.");
        } catch (SQLException e) {
            System.out.println("Failed to create the transaction history table.");
            e.printStackTrace();
        }
    }

    public static void logTransaction(String cardNumber, String transactionType, double amount) {
        createTransactionTable();

        String sql = "INSERT INTO TRANSACTION_HISTORY (CARDNUMBER, TRANSACTION_TYPE, AMOUNT, DATE_TIME) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL); PreparedStatement pstmt = connection.prepareStatement(sql)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String dateTime = dtf.format(LocalDateTime.now());

            pstmt.setString(1, cardNumber);
            pstmt.setString(2, transactionType);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, dateTime);

            pstmt.executeUpdate();
            System.out.println("Transaction logged: " + transactionType + " of $" + amount + " at " + dateTime);

        } catch (SQLException e) {
            System.out.println("Failed to log the transaction.");
            e.printStackTrace();
        }
    }

    public static void getTransactionHistory(String cardNumber) {
        String sql = "SELECT TRANSACTION_TYPE, AMOUNT, DATE_TIME FROM TRANSACTION_HISTORY WHERE CARDNUMBER = ? ORDER BY DATE_TIME DESC";

        try (Connection connection = DriverManager.getConnection(URL); PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);

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
