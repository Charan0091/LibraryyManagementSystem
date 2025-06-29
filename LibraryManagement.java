import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {

    // Book class
    static class Book {
        int id;
        String title;
        String author;
        boolean isIssued;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String toString() {
            return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Issued: " + isIssued;
        }
    }

    // User class
    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return "User ID: " + id + " | Name: " + name;
        }
    }

    // Library class
    static class Library {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        public void addBook(Book b) {
            books.add(b);
            System.out.println("Book added: " + b.title);
        }

        public void registerUser(User u) {
            users.add(u);
            System.out.println("User registered: " + u.name);
        }

        public void issueBook(int bookId, int userId) {
            Book book = findBook(bookId);
            User user = findUser(userId);
            if (book == null || user == null) {
                System.out.println("Book or User not found.");
                return;
            }
            if (book.isIssued) {
                System.out.println("Book already issued.");
                return;
            }
            book.isIssued = true;
            System.out.println("Book '" + book.title + "' issued to " + user.name);
        }

        public void returnBook(int bookId) {
            Book book = findBook(bookId);
            if (book == null) {
                System.out.println("Book not found.");
                return;
            }
            if (!book.isIssued) {
                System.out.println("Book was not issued.");
                return;
            }
            book.isIssued = false;
            System.out.println("Book '" + book.title + "' returned.");
        }

        public void viewBooks() {
            System.out.println("\n--- Book List ---");
            if (books.isEmpty()) {
                System.out.println("No books available.");
            }
            for (Book b : books) {
                System.out.println(b);
            }
        }

        public void viewUsers() {
            System.out.println("\n--- User List ---");
            if (users.isEmpty()) {
                System.out.println("No users registered.");
            }
            for (User u : users) {
                System.out.println(u);
            }
        }

        private Book findBook(int id) {
            for (Book b : books) {
                if (b.id == id) return b;
            }
            return null;
        }

        private User findUser(int id) {
            for (User u : users) {
                if (u.id == id) return u;
            }
            return null;
        }
    }

    // Main method
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. View Users");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.registerUser(new User(uid, name));
                }
                case 3 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    library.issueBook(bid, uid);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: ");
                    int bid = sc.nextInt();
                    library.returnBook(bid);
                }
                case 5 -> library.viewBooks();
                case 6 -> library.viewUsers();
                case 7 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 7);
        sc.close();
    }
}
