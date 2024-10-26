
## ATM GUI Application

### Overview

The ATM GUI Application simulates an ATM interface, allowing users to log in, sign up, view transaction options, and manage their bank accounts in a user-friendly graphical environment. The application includes a graphical main menu with options for logging in, signing up, viewing transactions, and exiting the program.

### Features

- **Main Menu**: The main entry point for navigation, offering options to log in, sign up, access transactions, or exit.
- **Login Screen**: Users can securely log in using their card number and PIN.
- **Signup Screen**: New users can sign up with personal details and create a secure PIN.
- **Transaction Menu**: After logging in, users can perform account-related actions like viewing their balance, depositing funds, and withdrawing funds.
- **Error Handling**: Error messages display for invalid login attempts, insufficient funds, and other cases, ensuring smooth user experience.

### Prerequisites

- **Java Development Kit (JDK)** version 8 or higher.
- **Java Swing** (included with JDK) for the graphical user interface.

### Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/kerthnorth/ModifiedATM.git
   cd atm-gui
   ```

2. **Compile the Program**:
   Navigate to the project directory and compile the Java files:
   ```bash
   javac -d bin -sourcepath src src/org/example/*.java
   ```

3. **Run the Program**:
   After compilation, run the main class to start the ATM GUI:
   ```bash
   java -cp bin org.example.ATM
   ```

### Usage Instructions

1. **Main Menu**:
   - On launching, the main menu presents four options: `Login`, `Signup`, `Transactions`, and `Exit`.

2. **Login**:
   - Choose "Login" to enter the login screen.
   - Input your card number and PIN to access your account. Upon successful login, you will be redirected to the transaction menu.

3. **Signup**:
   - Select "Signup" to register as a new user.
   - Fill in your details, including a secure 4-digit PIN.
   - Once signed up, you’ll be provided with a new card number for future logins.

4. **Transaction Menu**:
   - Access account-related actions (like viewing balance, depositing, and withdrawing funds) after logging in.
   - Each option is accessible through a button on this menu.

5. **Exit**:
   - Select "Exit" to close the application.

### Code Structure

- **ATM (Main Class)**: The entry point of the program, which initializes the GUI application.
- **ATMGUI**: Manages the main flow of screens, including the main menu, login, signup, and transaction menu screens.
- **LoginScreen, SignupScreen, TransactionMenu, MainMenuPanel**: Each of these classes represents a different screen in the ATM application, handling specific UI components and functionality.
- **UIHelper**: Contains utility methods for styling and reusable components across different screens (such as buttons and labels).

### Troubleshooting

- **Missing Logo Image**: Ensure the logo image file `numetro_logo.png` is located in the `resources/org/example/` directory. If the image is missing, an error message will appear on the screen.
- **Database Connectivity**: This application expects data access classes like `clientdata` to provide user authentication and account services. Ensure these dependencies are correctly configured.

### Extending the Application

For adding new features (like additional transactions or enhanced account management), modify `TransactionMenu` or the relevant UI panels, and implement your logic within `ATMService` or the `Database` package.

### License

This project is licensed under Paul Mukuna Kabeya License. See `LICENSE` for more information.

--- 

This `README.md` provides a comprehensive guide on setting up and running the application, as well as a quick overview of the features and functionality.