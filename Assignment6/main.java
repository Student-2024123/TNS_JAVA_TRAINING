import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        StringBuilder remaining = new StringBuilder();
        StringBuilder uppercase = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                uppercase.append(ch);
            } else {
                remaining.append(ch);
            }
        }

        System.out.println(remaining.toString() + uppercase.toString());

        sc.close();
    }
}