//package org.example.Database;
//
//import java.sql.*;
//
//import org.example.User;
//
//public class clientdata {
//    public static final String URL ="jdbc:sqlite:Clientdata.db";
//    private Connection conection;
//
//
////    public static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/Clientdata.db";
////    private Connection connection;
//
//    public static void createTable(Statement stmt) throws SQLException {
//        String createTableSQL = """
//        CREATE TABLE IF NOT EXISTS CLIENTDATA (
//            NAME varchar(255),
//            SURNAME varchar(255),
//            EMAIL varchar(255),
//            PIN varchar(255),
//            CARDNUMBER varchar(255),
//            BALANCE REAL DEFAULT 0.0 -- Add balance with default value
//        );
//        """;
//        // Execute the SQL statement to create the table
//        stmt.execute(createTableSQL);
//
//        System.out.println("Table 'CLIENTDATA' has been created or already exists.");
//    }
//
//    public static void createDatabase(User user) {
//        try (Connection connection = DriverManager.getConnection(URL);
//             Statement stmt = connection.createStatement()) {
//
//            // Call the method to create the table
//            createTable(stmt);
//
//            // Add the client after creating the table
//            addClients(user);
//
//        } catch (SQLException e) {
//            System.out.println("Failed to create the table or connect to the database.");
//            e.printStackTrace();
//        }
//    }
//
//    public static void addClients(User user) {
//        String sql = "INSERT INTO CLIENTDATA (NAME, SURNAME, EMAIL, PIN, CARDNUMBER, BALANCE) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (Connection connection = DriverManager.getConnection(URL);
//             PreparedStatement pstmt = connection.prepareStatement(sql)) {
//
//            pstmt.setString(1, user.getName());
//            pstmt.setString(2, user.getSurname());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setString(4, String.valueOf(user.getPin()));
//            pstmt.setString(5, String.valueOf(user.getCardNumber()));
//            pstmt.setDouble(6, 0.0); // Set initial balance to 0.0
//
//            pstmt.executeUpdate();
//            System.out.println("New client added to the database with an initial balance of 0.0.");
//        } catch (SQLException e) {
//            System.out.println("Failed to add new client to the database.");
//            e.printStackTrace();
//        }
//    }
//    public static double getUserBalance(String cardNumber) {
//        String sql = "SELECT BALANCE FROM CLIENTDATA WHERE CARDNUMBER = ?";
//
//        try (Connection connection = DriverManager.getConnection(URL);
//             PreparedStatement pstmt = connection.prepareStatement(sql)) {
//
//            pstmt.setString(1, cardNumber);
//
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getDouble("BALANCE");
//            } else {
//                System.out.println("No balance found for the provided card number.");
//                return 0.0;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Failed to retrieve user balance.");
//            e.printStackTrace();
//            return 0.0;
//        }
//    }
//
//
//
//    // This method checks if the provided card number and pin exist in the database
//    public static boolean isValidLogin(String cardNumber, String pin) {
//        String sql = "SELECT EMAIL, BALANCE FROM CLIENTDATA WHERE CARDNUMBER = ? AND PIN = ?";
//
//        try (Connection connection = DriverManager.getConnection(URL);
//             PreparedStatement pstmt = connection.prepareStatement(sql)) {
//
//            pstmt.setString(1, cardNumber);  // Set the card number parameter
//            pstmt.setString(2, pin);         // Set the pin parameter
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                String email = rs.getString("EMAIL");
//                double balance = rs.getDouble("BALANCE");
//                System.out.println("Login successful! Email associated: " + email);
//                System.out.println("Current balance: $" + balance);
//                return true;
//            } else {
//                System.out.println("Invalid login. The card number and pin are not linked.");
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Failed to check the database for login.");
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//}
package org.example.Database;

import org.example.User;

import java.sql.*;

public class clientdata {
    public static final String URL = "jdbc:sqlite:Clientdata.db";
    private Connection connection;

    // Method to create the CLIENTDATA table with balance support
    public static void createTable(Statement stmt) throws SQLException {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS CLIENTDATA (
            NAME varchar(255),
            SURNAME varchar(255),
            EMAIL varchar(255),
            PIN varchar(255),
            CARDNUMBER varchar(255),
            BALANCE REAL DEFAULT 0.0 -- Add balance with default value
        );
        """;
        // Execute the SQL statement to create the table
        stmt.execute(createTableSQL);
        System.out.println("Table 'CLIENTDATA' has been created or already exists.");
    }

    // Method to create the database and add a new client
    public static void createDatabase(User user) {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement()) {

            // Call the method to create the table
            createTable(stmt);

            // Add the client after creating the table
            addClients(user);

        } catch (SQLException e) {
            System.out.println("Failed to create the table or connect to the database.");
            e.printStackTrace();
        }
    }

    // Method to add a client with an initial balance of 0.0
    public static void addClients(User user) {
        String sql = "INSERT INTO CLIENTDATA (NAME, SURNAME, EMAIL, PIN, CARDNUMBER, BALANCE) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, String.valueOf(user.getPin()));
            pstmt.setString(5, String.valueOf(user.getCardNumber()));
            pstmt.setDouble(6, 0.0); // Set initial balance to 0.0

            pstmt.executeUpdate();
            System.out.println("New client added to the database with an initial balance of 0.0.");
        } catch (SQLException e) {
            System.out.println("Failed to add new client to the database.");
            e.printStackTrace();
        }
    }

    // Method to retrieve the user's balance based on their card number
    public static double getUserBalance(String cardNumber) {
        String sql = "SELECT BALANCE FROM CLIENTDATA WHERE CARDNUMBER = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("BALANCE");
            } else {
                System.out.println("No balance found for the provided card number.");
                return 0.0;
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve user balance.");
            e.printStackTrace();
            return 0.0;
        }
    }

    // Method to update the user's balance after deposit or withdrawal
    public static void updateUserBalance(String cardNumber, double newBalance) {
        String sql = "UPDATE CLIENTDATA SET BALANCE = ? WHERE CARDNUMBER = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, cardNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User balance updated successfully.");
            } else {
                System.out.println("Failed to update the balance. Card number not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update user balance.");
            e.printStackTrace();
        }
    }

    // Method to check if the provided card number and pin exist in the database
    public static boolean isValidLogin(String cardNumber, String pin) {
        String sql = "SELECT EMAIL, BALANCE FROM CLIENTDATA WHERE CARDNUMBER = ? AND PIN = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);  // Set the card number parameter
            pstmt.setString(2, pin);         // Set the pin parameter

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString("EMAIL");
                double balance = rs.getDouble("BALANCE");
                System.out.println("Login successful! Email associated: " + email);
                System.out.println("Current balance: $" + balance);
                return true;
            } else {
                System.out.println("Invalid login. The card number and pin are not linked.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Failed to check the database for login.");
            e.printStackTrace();
            return false;
        }
    }
}

