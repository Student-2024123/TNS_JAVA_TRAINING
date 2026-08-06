package Assignment4;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " into Savings Account.");
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " from Savings Account.");
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}