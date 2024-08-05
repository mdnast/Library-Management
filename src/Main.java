import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Add new book");
            System.out.println("2. Display book list");
            System.out.println("3. Delete book by code");
            System.out.println("4. Sort books by rating (descending)");
            System.out.println("5. Search book by code or title");
            System.out.println("6. Filter books with rating >= x");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.print("Enter book code to delete: ");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 4:
                    sortByRatingDesc();
                    break;
                case 5:
                    System.out.print("Enter book code or title to search: ");
                    String keyword = scanner.nextLine();
                    Book foundBook = findByCodeOrTitle(keyword);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter rating threshold: ");
                    float x = scanner.nextFloat();
                    List<Book> filteredBooks = filterByRating(x);
                    if (!filteredBooks.isEmpty()) {
                        System.out.println("Books with rating >= " + x + ":");
                        for (Book book : filteredBooks) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("No books found with rating >= " + x);
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book information:");
        System.out.print("Code: ");
        String code = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Rating: ");
        float rating = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        Book book = new Book(code, title, author, publisher, rating);
        bookList.add(book);
    }

    public static void output() {
        System.out.println("Book list:");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public static void removeByCode(String code) {
        bookList.removeIf(book -> book.getCode().equalsIgnoreCase(code));
        System.out.println("Book with code " + code + " has been removed.");
    }

    public static void sortByRatingDesc() {
        bookList.sort(Comparator.comparing(Book::getRating).reversed());
        System.out.println("Books sorted by rating in descending order.");
    }

    public static Book findByCodeOrTitle(String keyword) {
        for (Book book : bookList) {
            if (book.getCode().equalsIgnoreCase(keyword) || book.getTitle().equalsIgnoreCase(keyword)) {
                return book;
            }
        }
        return null;
    }

    public static List<Book> filterByRating(float x) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getRating() >= x) {
                result.add(book);
            }
        }
        return result;
    }
}
