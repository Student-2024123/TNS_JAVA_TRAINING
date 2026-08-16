
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Booking details");

        String input = sc.nextLine();

        String[] details = input.split(",");

        String stageEvent = details[0];
        String customer = details[1];
        Integer noOfSeats = Integer.parseInt(details[2]);

        TicketBooking booking =
                new TicketBooking(stageEvent, customer, noOfSeats);

        System.out.println("Payment mode");
        System.out.println("1.Cash payment");
        System.out.println("2.Wallet payment");
        System.out.println("3.Credit card payment");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.println("Enter the amount");

                Double cashAmount = sc.nextDouble();

                booking.makePayment(cashAmount);
                break;

            case 2:
                System.out.println("Enter the amount");

                Double walletAmount = sc.nextDouble();

                sc.nextLine();

                System.out.println("Enter the wallet number");

                String walletNumber = sc.nextLine();

                booking.makePayment(walletNumber, walletAmount);
                break;

            case 3:
                sc.nextLine();

                System.out.println("Enter card holder name");

                String name = sc.nextLine();

                System.out.println("Enter the amount");

                Double cardAmount = sc.nextDouble();

                sc.nextLine();

                System.out.println("Enter the credit card type");

                String creditCard = sc.nextLine();

                System.out.println("Enter the CCV number");

                String ccv = sc.nextLine();

                booking.makePayment(creditCard, ccv, name, cardAmount);
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}