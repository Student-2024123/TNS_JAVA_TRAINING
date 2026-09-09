package Assignment7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();

        // Create bank account
        BankAccount account = new BankAccount(accountNumber, initialBalance);

        int choice;

        do {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();

                    try {
                        account.deposit(depositAmount);
                        System.out.println("Deposit operation completed.");
                    } catch (InvalidAmountException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        System.out.println("---------------------------");
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();

                    try {
                        account.withdraw(withdrawAmount);
                        System.out.println("Withdrawal operation completed.");
                    } catch (InvalidAmountException | InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        System.out.println("---------------------------");
                    }
                    break;

                case 3:
                    account.displayBalance();
                    break;

                case 4:
                    System.out.println("Thank you for using the Banking System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
