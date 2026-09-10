package Assignment9;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Choice (1 for AirIndia, 2 for KingFisher, 3 for Indigo):");
        int choice = scanner.nextInt();

        System.out.println("Enter hours of travel:");
        int hours = scanner.nextInt();

        System.out.println("Enter cost per hour:");
        double costPerHour = scanner.nextDouble();

        Airfare airfare = null;

        switch (choice) {
            case 1:
                airfare = new AirIndia(hours, costPerHour);
                break;
            case 2:
                airfare = new KingFisher(hours, costPerHour);
                break;
            case 3:
                airfare = new Indigo(hours, costPerHour);
                break;
            default:
                System.out.println("Invalid Choice");
                scanner.close();
                return;
        }

        double totalAmount = airfare.calculateAmount();

        System.out.print("Total Amount: ");
        System.out.printf("%.2f\n", totalAmount);
    }
}