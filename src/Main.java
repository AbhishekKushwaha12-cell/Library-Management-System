import service.LibraryService;
import util.FileUtil;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileUtil.initializeFile();
        LibraryService service = new LibraryService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Show All Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    if (service.addBook(isbn, title, author))
                        System.out.println("Book added.");
                    else
                        System.out.println("ISBN already exists.");
                }
                case 2 -> {
                    System.out.print("Enter ISBN to remove: ");
                    String isbn = sc.nextLine();
                    if (service.removeBook(isbn))
                        System.out.println("Book removed.");
                    else
                        System.out.println("Book not found.");
                }
                case 3 -> {
                    System.out.print("Enter title to search: ");
                    String title = sc.nextLine();
                    List<Book> results = service.searchByTitle(title);
                    if (results.isEmpty())
                        System.out.println("No books found.");
                    else
                        results.forEach(book -> System.out.println(book.toFileString()));
                }
                case 4 -> {
                    List<Book> all = service.getAllBooks();
                    if (all.isEmpty())
                        System.out.println("Library is empty.");
                    else
                        all.forEach(book -> System.out.println(book.toFileString()));
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
