package Assignment11;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY BOOK MANAGEMENT SYSTEM =====");
            System.out.println("1. Add New Book");
            System.out.println("2. Display Information About a Book");
            System.out.println("3. Update Number of Copies Available");
            System.out.println("4. Display List of Books Sorted by Title");
            System.out.println("5. Display List of Books Sorted by Author");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine().trim();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine().trim();

                    System.out.print("Enter Publication: ");
                    String publication = scanner.nextLine().trim();

                    int year = 0;
                    int copies = 0;

                    try {
                        System.out.print("Enter Publication Year: ");
                        year = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("Enter Number of Copies Available: ");
                        copies = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Year and Copies must be valid integers.");
                        break;
                    }

                    Book book = new Book(title, author, isbn, publication, year, copies);
                    if (library.addBook(book)) {
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("Error: A book with ISBN '" + isbn + "' already exists.");
                    }
                    break;

                case 2:
                    System.out.print("Enter ISBN to search: ");
                    String searchIsbn = scanner.nextLine().trim();
                    Book foundBook = library.getBookByIsbn(searchIsbn);
                    if (foundBook != null) {
                        System.out.println("\nBook Details:");
                        System.out.println(foundBook);
                    } else {
                        System.out.println("Book not found with ISBN: " + searchIsbn);
                    }
                    break;

                case 3:
                    System.out.print("Enter ISBN to update copies: ");
                    String updateIsbn = scanner.nextLine().trim();
                    try {
                        System.out.print("Enter new copy count: ");
                        int newCopies = Integer.parseInt(scanner.nextLine().trim());
                        if (library.updateCopies(updateIsbn, newCopies)) {
                            System.out.println("Copies updated successfully!");
                        } else {
                            System.out.println("Book not found with ISBN: " + updateIsbn);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid integer for copy count.");
                    }
                    break;

                case 4:
                    List<Book> booksByTitle = library.getBooksSortedByTitle();
                    if (booksByTitle.isEmpty()) {
                        System.out.println("No books in library.");
                    } else {
                        System.out.println("\n--- Books Sorted Alphabetically by Title ---");
                        for (Book b : booksByTitle) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 5:
                    List<Book> booksByAuthor = library.getBooksSortedByAuthor();
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("No books in library.");
                    } else {
                        System.out.println("\n--- Books Sorted Alphabetically by Author ---");
                        for (Book b : booksByAuthor) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select an option from 1 to 6.");
            }
        }
    }
}
