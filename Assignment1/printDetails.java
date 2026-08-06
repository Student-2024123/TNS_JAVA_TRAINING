package Assignment1;
import java.util.*;

public class printDetails {
    public void getData() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = scn.nextLine();
        // Taking input for roll no
        System.out.print("Enter Student Roll No: ");
        int rollNo = scn.nextInt();

        System.out.print("Enter Student Grade: ");
        String grade = scn.next();

        System.out.print("Enter Student Percentage: ");
        double per = scn.nextDouble();

        System.out.println(name);
        System.out.println(rollNo);
        System.out.println(grade);
        System.out.println(per);
        scn.close();

    }

    public static void main(String[] args) {
        printDetails s1 = new printDetails();
        s1.getData();
    }

}