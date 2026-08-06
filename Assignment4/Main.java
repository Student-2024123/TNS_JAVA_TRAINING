package Assignment4;

public class Main {

    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount("Dipesh", 10000);
        CheckingAccount ca = new CheckingAccount("Rahul", 15000);

        Transaction transaction = new Transaction();

        System.out.println("------ Savings Account ------");
        sa.displayDetails();
        transaction.performTransaction(sa, "deposit", 5000);
        transaction.performTransaction(sa, "withdraw", 2000);

        System.out.println();

        System.out.println("------ Checking Account ------");
        ca.displayDetails();
        transaction.performTransaction(ca, "deposit", 3000);
        transaction.performTransaction(ca, "withdraw", 5000);

        System.out.println();

        System.out.println("Total Accounts Created : " + Bank.getTotalAccounts());
    }
}