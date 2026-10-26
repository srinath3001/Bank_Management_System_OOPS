// ============================================================================
// 3. CHILD CLASS: SavingsAccount
// ============================================================================
// Demonstrates INHERITANCE:
// Inherits properties (accountNumber, balance, etc.) and methods from BankAccount.
// Special Rule: Requires a minimum balance of ₹500 at all times.
public class SavingsAccount extends BankAccount {

    // Minimum balance constant for savings accounts
    private static final double MINIMUM_BALANCE = 500.0;

    // Constructor calls the parent class constructor using 'super'
    public SavingsAccount(int accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance, "Savings Account");
    }

    // Overriding the abstract withdraw method (Polymorphism & Abstraction)
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return;
        }

        if (amount > getBalance()) {
            System.out.println("Error: Insufficient balance. Available balance: ₹" + String.format("%.2f", getBalance()));
            return;
        }

        // Check if the balance after withdrawal stays at or above the minimum requirement of ₹500
        if (getBalance() - amount < MINIMUM_BALANCE) {
            System.out.println("Error: Cannot withdraw ₹" + String.format("%.2f", amount) + ".");
            System.out.println("Savings account must maintain a minimum balance of ₹" + String.format("%.2f", MINIMUM_BALANCE) + ".");
            System.out.println("Maximum amount you can withdraw right now: ₹" + String.format("%.2f", (getBalance() - MINIMUM_BALANCE)));
            return;
        }

        // Deduct the money safely
        deductBalance(amount);
        System.out.println("Success: ₹" + String.format("%.2f", amount) + " withdrawn successfully.");
        System.out.println("Remaining Balance: ₹" + String.format("%.2f", getBalance()));
    }
}
