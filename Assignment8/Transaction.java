package Assignment8;

public class Transaction extends Thread {

    private Account account;
    private String type;
    private double amount;

    public Transaction(
            Account account,
            String type,
            double amount,
            String threadName) {

        super(threadName);

        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {

        System.out.println(
                Thread.currentThread().getName() + " started.");

        if (type.equalsIgnoreCase("deposit")) {

            account.deposit(amount);

        } else if (type.equalsIgnoreCase("withdraw")) {

            account.withdraw(amount);

        } else {

            System.out.println("Invalid transaction type.");
        }

        System.out.println(
                Thread.currentThread().getName() + " completed.");
    }
}
