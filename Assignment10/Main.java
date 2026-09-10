package Assignment10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the array (n):");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }
        int n = scanner.nextInt();

        TestScoreTracker tracker = new TestScoreTracker(n);
        int[] scores = tracker.getScoresArray();

        System.out.println("Enter " + n + " space-separated integer test scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        tracker.convertAndDisplay();
    }
}
