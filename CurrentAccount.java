// ============================================================================
// 4. CHILD CLASS: CurrentAccount
// ============================================================================
// Demonstrates INHERITANCE:
// Inherits from BankAccount.
// Rule: No minimum balance restriction. Withdrawals are allowed as long as balance is sufficient.
public class CurrentAccount extends BankAccount {

    // Constructor calls the parent class constructor using 'super'
    public CurrentAccount(int accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance, "Current Account");
    }

    // Overriding the abstract withdraw method (Polymorphism & Abstraction)
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return;
        }

        // In Current Account, user can withdraw any amount as long as balance is sufficient
        if (amount > getBalance()) {
            System.out.println("Error: Insufficient balance. Available balance: ₹" + String.format("%.2f", getBalance()));
            return;
        }

        // Deduct the money safely
        deductBalance(amount);
        System.out.println("Success: ₹" + String.format("%.2f", amount) + " withdrawn successfully.");
        System.out.println("Remaining Balance: ₹" + String.format("%.2f", getBalance()));
    }
}
