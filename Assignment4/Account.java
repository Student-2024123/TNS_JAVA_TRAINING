package Assignment4;

public abstract class Account {

    protected String accountHolder;
    protected double balance;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;

        // Count every account created
        new Bank();
    }

    // Concrete method
    public void displayDetails() {
        System.out.println("Account Holder : " + accountHolder);
    }

    // Abstract methods
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract double getBalance();
}