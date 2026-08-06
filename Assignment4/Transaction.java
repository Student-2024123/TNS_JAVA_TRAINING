package Assignment4;

public class Transaction {

    public static final double transactionFee = 10.0;

    public final void performTransaction(Account account,
                                         String type,
                                         double amount) {

        if (type.equalsIgnoreCase("deposit")) {
            account.deposit(amount - transactionFee);
            System.out.println("Transaction Fee : ₹" + transactionFee);
        }
        else if (type.equalsIgnoreCase("withdraw")) {
            account.withdraw(amount + transactionFee);
            System.out.println("Transaction Fee : ₹" + transactionFee);
        }
        else {
            System.out.println("Invalid Transaction Type.");
        }

        System.out.println("Current Balance : ₹" + account.getBalance());
    }
}