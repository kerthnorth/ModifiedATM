
import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class ATMSystem {

    private ATMService atmService;

    public ATMSystem() {
        atmService = new ATMService(); // Initialize ATMService
    }

    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        JFrame frame = new JFrame("ATM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        atmSystem.showMainMenu(frame);
        frame.setVisible(true);
    }

    private void showMainMenu(JFrame frame) {
        // ...
        // Code for showing the main menu
        // ...
    }

    // Other methods for signup, login, and transaction functionalities
    // ...
}

class User {

    private String name;
    private String surname;
    private String email;
    private String pin;
    private String cardNumber;

    // Getters and setters for user properties
    // ...
}

class ATMService {
    // ...
    // Code for ATM service functionalities
    // ...
}

// Other classes and interfaces
// ...
