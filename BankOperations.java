// ============================================================================
// 1. INTERFACE: BankOperations
// ============================================================================
// An interface defines the standard operations that any bank account must support.
// In simple terms, it acts as a contract specifying WHAT operations can be done,
// leaving HOW they are done to the classes that implement it.
public interface BankOperations {

    void deposit(double amount);

    void withdraw(double amount);

    void checkBalance();
}
