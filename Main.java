import java.util.Scanner;

// ============================================================================
// 5. MAIN CLASS: Main
// ============================================================================
// Contains the console menu, Scanner input, account storage array, and operations.
public class Main {

    // Array to store up to 100 bank accounts (No database or collections used)
    private static BankAccount[] accounts = new BankAccount[100];
    private static int totalAccounts = 0;

    // Automatic unique account number counter starting from 1001
    private static int nextAccountNumber = 1001;

    // Scanner for reading console inputs
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        // Display menu repeatedly until user chooses Exit (Option 7)
        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Display Account Details");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for menu choice
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear newline buffer
            } else {
                System.out.println("Invalid input! Please enter a valid number (1 to 7).");
                scanner.nextLine(); // Discard invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkAccountBalance();
                    break;
                case 5:
                    displayAccountDetails();
                    break;
                case 6:
                    displayAllAccounts();
                    break;
                case 7:
                    System.out.println("\nThank you for using Bank Management System! Have a great day.");
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 7.");
            }
        } while (choice != 7);

        scanner.close();
    }

    // ------------------------------------------------------------------------
    // Helper Method: Find account in array by account number using a simple loop
    // ------------------------------------------------------------------------
    private static BankAccount findAccount(int accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i]; // Found matching account
            }
        }
        return null; // Account not found
    }

    // ------------------------------------------------------------------------
    // Feature 1: Create a new account
    // ------------------------------------------------------------------------
    private static void createAccount() {
        if (totalAccounts >= accounts.length) {
            System.out.println("Error: Bank storage is full! Cannot open more accounts.");
            return;
        }

        System.out.println("\n--- Create New Account ---");
        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account (Requires minimum balance of ₹500.00)");
        System.out.println("2. Current Account (No minimum balance requirement)");
        System.out.print("Enter account type choice (1 or 2): ");

        int typeChoice;
        if (scanner.hasNextInt()) {
            typeChoice = scanner.nextInt();
            scanner.nextLine(); // Clear newline buffer
        } else {
            System.out.println("Invalid input! Account creation cancelled.");
            scanner.nextLine(); // Clear invalid input
            return;
        }

        if (typeChoice != 1 && typeChoice != 2) {
            System.out.println("Invalid account type selected! Account creation cancelled.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Account holder name cannot be empty. Account creation cancelled.");
            return;
        }

        System.out.print("Enter Initial Deposit Amount: ₹");
        double initialDeposit;
        if (scanner.hasNextDouble()) {
            initialDeposit = scanner.nextDouble();
            scanner.nextLine(); // Clear newline buffer
        } else {
            System.out.println("Invalid deposit amount! Account creation cancelled.");
            scanner.nextLine(); // Clear invalid input
            return;
        }

        // Validate initial deposit for Savings Account (must be at least ₹500)
        if (typeChoice == 1) {
            if (initialDeposit < 500.0) {
                System.out.println("Error: Initial deposit for Savings Account must be at least ₹500.00. Account creation cancelled.");
                return;
            }
            int accNum = nextAccountNumber++;
            accounts[totalAccounts] = new SavingsAccount(accNum, name, initialDeposit);
            totalAccounts++;
            System.out.println("\nSuccess: Savings Account created successfully!");
            System.out.println("Assigned Account Number: " + accNum);
        } else {
            // Validate initial deposit for Current Account (must be greater than or equal to 0)
            if (initialDeposit < 0) {
                System.out.println("Error: Initial deposit cannot be negative. Account creation cancelled.");
                return;
            }
            int accNum = nextAccountNumber++;
            accounts[totalAccounts] = new CurrentAccount(accNum, name, initialDeposit);
            totalAccounts++;
            System.out.println("\nSuccess: Current Account created successfully!");
            System.out.println("Assigned Account Number: " + accNum);
        }
    }

    // ------------------------------------------------------------------------
    // Feature 2: Deposit money into an account
    // ------------------------------------------------------------------------
    private static void depositMoney() {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter Account Number: ");

        int accNum;
        if (scanner.hasNextInt()) {
            accNum = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid account number format!");
            scanner.nextLine();
            return;
        }

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Error: Account with number " + accNum + " not found!");
            return;
        }

        System.out.print("Enter Amount to Deposit: ₹");
        double amount;
        if (scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } else {
            System.out.println("Invalid amount format!");
            scanner.nextLine();
            return;
        }

        account.deposit(amount);
    }

    // ------------------------------------------------------------------------
    // Feature 3: Withdraw money from an account
    // ------------------------------------------------------------------------
    private static void withdrawMoney() {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter Account Number: ");

        int accNum;
        if (scanner.hasNextInt()) {
            accNum = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid account number format!");
            scanner.nextLine();
            return;
        }

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Error: Account with number " + accNum + " not found!");
            return;
        }

        System.out.print("Enter Amount to Withdraw: ₹");
        double amount;
        if (scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } else {
            System.out.println("Invalid amount format!");
            scanner.nextLine();
            return;
        }

        // Demonstrates Polymorphism:
        // Java automatically calls the withdraw() method of the specific account type (Savings or Current)
        account.withdraw(amount);
    }

    // ------------------------------------------------------------------------
    // Feature 4: Check balance of an account
    // ------------------------------------------------------------------------
    private static void checkAccountBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.print("Enter Account Number: ");

        int accNum;
        if (scanner.hasNextInt()) {
            accNum = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid account number format!");
            scanner.nextLine();
            return;
        }

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Error: Account with number " + accNum + " not found!");
            return;
        }

        account.checkBalance();
    }

    // ------------------------------------------------------------------------
    // Feature 5: Display details of a specific account
    // ------------------------------------------------------------------------
    private static void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.print("Enter Account Number: ");

        int accNum;
        if (scanner.hasNextInt()) {
            accNum = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid account number format!");
            scanner.nextLine();
            return;
        }

        BankAccount account = findAccount(accNum);
        if (account == null) {
            System.out.println("Error: Account with number " + accNum + " not found!");
            return;
        }

        account.displayDetails();
    }

    // ------------------------------------------------------------------------
    // Feature 6: Display all accounts created so far
    // ------------------------------------------------------------------------
    private static void displayAllAccounts() {
        System.out.println("\n--- All Bank Accounts ---");
        if (totalAccounts == 0) {
            System.out.println("No accounts found in the system. Please create an account first.");
            return;
        }

        System.out.println("Total Accounts Registered: " + totalAccounts);
        for (int i = 0; i < totalAccounts; i++) {
            System.out.println("\nAccount #" + (i + 1) + ":");
            accounts[i].displayDetails();
        }
    }
}
