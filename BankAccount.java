// ============================================================================
// 2. ABSTRACT CLASS: BankAccount
// ============================================================================
// Demonstrates both ENCAPSULATION and ABSTRACTION:
// - Encapsulation: All account data variables are marked 'private' so outside
//   code cannot tamper with balances directly. Safe access is provided via getters.
// - Abstraction: It provides common code for deposit and display, but leaves
//   'withdraw' abstract because Savings and Current accounts have different rules.
public abstract class BankAccount implements BankOperations {

    // Encapsulation: Private member variables
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;

    // Constructor to initialize account details
    public BankAccount(int accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Getters to safely read private data (Encapsulation)
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    // Common deposit method implementation for all account types
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Success: ₹" + String.format("%.2f", amount) + " deposited successfully.");
            System.out.println("Updated Balance: ₹" + String.format("%.2f", balance));
        } else {
            System.out.println("Error: Deposit amount must be greater than zero.");
        }
    }

    // Abstract method: Forces each child class to implement its own withdrawal rules (Abstraction)
    @Override
    public abstract void withdraw(double amount);

    // Common checkBalance method
    @Override
    public void checkBalance() {
        System.out.println("\n--- Balance Information ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Current Balance: ₹" + String.format("%.2f", balance));
        System.out.println("---------------------------");
    }

    // Display complete details of the account
    public void displayDetails() {
        System.out.println("----------------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : ₹" + String.format("%.2f", balance));
        System.out.println("----------------------------------------");
    }

    // Helper method to safely reduce balance after withdrawal rules pass
    protected void deductBalance(double amount) {
        this.balance = this.balance - amount;
    }
}
