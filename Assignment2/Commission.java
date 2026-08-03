package Assignment2;
import java.util.*;

public class Commission {
    String Name;
    String Address;
    long Phone;
    double Sales_amount;

    public void getData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        Name = sc.nextLine();

        System.out.print("Enter Address: ");
        Address = sc.nextLine();

        System.out.print("Enter Phone: ");
        Phone = sc.nextLong();

        System.out.print("Enter Sales Amount: ");
        Sales_amount = sc.nextDouble();
    }

    public void calCommission() {
        if (Sales_amount >= 100000)
            System.out.println("Commission = 10%");
        else if (Sales_amount >= 50000)
            System.out.println("Commission = 5%");
        else if (Sales_amount >= 30000)
            System.out.println("Commission = 3%");
        else
            System.out.println("No Commission");
    }

    public static void main(String[] args) {
        Commission c1 = new Commission();

        c1.getData();

        c1.calCommission();
    }
}