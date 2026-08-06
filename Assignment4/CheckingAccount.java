package Assignment4;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " into Checking Account.");
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " from Checking Account.");
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}