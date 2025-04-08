import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;

public class BooksManager {
    Scanner sc = new Scanner(System.in);
    private HashMap<String, Integer> books;
    private HashMap<String, Date> borrowedBooks;

    public BooksManager() {
        books = new HashMap<>();
        borrowedBooks = new HashMap<>();
        addBooks("Os Segredos da Mente Milionária");
        addBooks("7 Hábitos das Pessoas Altamente Eficazes");
        addBooks("Ponto de Inflexão");
        addBooks("Nunca É Hora de Parar");
    }

    private void addBooks(String title) {
        Random rand = new Random();
        int quantity = rand.nextInt(25) + 1;
        books.put(title, quantity);
    }

    public void displayBooks() {
        System.out.println();
        System.out.println("Available books:");
        for (String book : books.keySet()) {
            System.out.println("- "  + book + " (" + books.get(book) + ")");
        }
    }

    public void borrowBooks() {
        while (true) {
            displayBooks();
            System.out.println();
            System.out.println("Enter the titles of the books you want to borrow, separated by commas:");
            String[] borrowedBooksTitles = sc.nextLine().split(",");

            for (String title : borrowedBooksTitles) {
                title = title.trim();
                if (isValidTitle(title)) {
                    if (books.containsKey(title) && books.get(title) > 0) {
                        books.put(title, books.get(title) - 1);
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_YEAR, 5);
                        Date dueDate = calendar.getTime();
                        borrowedBooks.put(title, dueDate);
                        System.out.println();
                        System.out.println("You have borrowed '" + title + "'. Please return it by " + dueDate);
                    } else {
                        System.out.println();
                        System.out.println("'" + title + "' is not available.");
                    }
                } else {
                    System.out.println("'" + title + "' is not a valid title.");
                }
            }

            System.out.println();
            String moreBooks;
            while (true) {
                System.out.println();
                System.out.println("Do you want to borrow more books? (yes/no):");
                moreBooks = sc.nextLine().trim().toLowerCase();
                if (moreBooks.equals("yes") || moreBooks.equals("no")) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
            if (moreBooks.equals("no")) {
                break;
            }
        }
    }

    public void returnBooks() {
        System.out.println();
        System.out.println("Books you have borrowed:");
        for (String book : borrowedBooks.keySet()) {
            System.out.println("- " + book + " (due by " + borrowedBooks.get(book) + ")");
        }

        System.out.println();
        System.out.println("Enter the titles of the books you want to return, separated by commas:");
        String[] returnBooksTitles = sc.nextLine().split(",");

        for (String title : returnBooksTitles) {
            title = title.trim();
            if (borrowedBooks.containsKey(title)) {
                borrowedBooks.remove(title);
                books.put(title, books.get(title) + 1);
                System.out.println("You have returned '" + title + "'. Thank you!");
            } else {
                System.out.println("'" + title + "' was not borrowed.");
            }
        }
    }

    private boolean isValidTitle(String title) {
        return title.matches("[\\p{L}\\d ]+");
    }

    public static void main(String[] args) {
        BooksManager manager = new BooksManager();
        manager.borrowBooks();
    }
}
