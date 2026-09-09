package Assignment8;

public class Account {

    private int accountNumber;
    private String customerName;
    private double balance;

    public Account(int accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {

        System.out.println(Thread.currentThread().getName() + " depositing Rs" + amount);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        balance += amount;

        System.out.println("Deposit successful. Balance = Rs" + balance);
    }

    public synchronized void withdraw(double amount) {

        System.out.println(Thread.currentThread().getName() + " withdrawing Rs" + amount);

        if (balance >= amount) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            balance -= amount;

            System.out.println("Withdrawal successful. Balance = Rs" + balance);

        } else {

            System.out.println("Withdrawal failed. Insufficient balance.");
        }
    }

    public synchronized void displayAccount() {

        System.out.println("\nAccount Number : " + accountNumber);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Balance        : Rs" + balance);
    }
}
