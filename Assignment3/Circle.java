package Assignment3;

import java.util.Scanner;

public class Circle {
    double radius;
    String color;

    public void getData() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter radius of circle: ");
        radius = scn.nextDouble();

        System.out.print("Enter Color of circle: ");
        color = scn.next();
        scn.close();
    }

    public double calArea() {
        return 3.14 * radius * radius;
    }

    public static void main(String[] args) {
        Circle c1 = new Circle();

        c1.getData();

        System.out.println("Area of Circle = " + c1.calArea() + " units");
    }
}
