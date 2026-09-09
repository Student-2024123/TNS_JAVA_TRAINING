package Assignment8;

public class BankingSystem {

    public static void main(String[] args) {

        System.out.println("===== BANKING SYSTEM =====");

        // Create accounts
        Account account1 = new Account(101, "Rahul", 10000);

        Account account2 = new Account(102, "Priya", 15000);

        // Display initial details
        System.out.println("\nInitial Account Details:");

        account1.displayAccount();
        account2.displayAccount();

        // Create transactions
        Transaction t1 = new Transaction(
                account1,
                "withdraw",
                3000,
                "Transaction-1");

        Transaction t2 = new Transaction(
                account1,
                "deposit",
                5000,
                "Transaction-2");

        Transaction t3 = new Transaction(
                account2,
                "withdraw",
                4000,
                "Transaction-3");

        Transaction t4 = new Transaction(
                account2,
                "deposit",
                2000,
                "Transaction-4");

        // Start threads
        System.out.println("\nStarting transactions...");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads
        try {

            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        // Display final details
        System.out.println("\n===== FINAL ACCOUNT DETAILS =====");

        account1.displayAccount();
        account2.displayAccount();

        // Display final thread states
        System.out.println("\nThread States:");

        System.out.println("Transaction-1 : " + t1.getState());

        System.out.println("Transaction-2 : " + t2.getState());

        System.out.println("Transaction-3 : " + t3.getState());

        System.out.println("Transaction-4 : " + t4.getState());
    }
}
