import java.util.*;

public class BooksManager {
    Scanner sc = new Scanner(System.in);
    private HashMap<String, Integer> books;
    private HashMap<String, User> registeredUsers;
    private HashMap<String, List<BorrowedBook>> userBorrowedBooks;

    public BooksManager() {
        books = new HashMap<>();
        this.registeredUsers = registeredUsers;
        userBorrowedBooks = new HashMap<>();
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
        System.out.println("\nAvailable books:");
        for (String book : books.keySet()) {
            System.out.println("- " + book + " (" + books.get(book) + ")");
        }
    }

    public void borrowBooks() {
        System.out.print("Enter your username: ");
        String username = sc.nextLine().trim();

        User user = registeredUsers.get(username);
        if (user == null) {
            System.out.println("User not found. Please register first.");
            return;
        }

        while (true) {
            displayBooks();
            System.out.println("\nEnter the titles of the books you want to borrow, separated by commas:");
            String[] borrowedBooksTitles = sc.nextLine().split(",");

            for (String title : borrowedBooksTitles) {
                title = title.trim();
                if (isValidTitle(title)) {
                    if (books.containsKey(title) && books.get(title) > 0) {
                        books.put(title, books.get(title) - 1);
                        Calendar calendar = Calendar.getInstance();
                        Date borrowedDate = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, 5);
                        Date dueDate = calendar.getTime();

                        BorrowedBook borrowedBook = new BorrowedBook(title, borrowedDate, dueDate);
                        userBorrowedBooks.computeIfAbsent(username, k -> new ArrayList<>()).add(borrowedBook);

                        System.out.println("\n" + user.getNomeCompleto() + " borrowed '" + title + "'. Return by " + dueDate);
                    } else {
                        System.out.println("\n'" + title + "' is not available.");
                    }
                } else {
                    System.out.println("'" + title + "' is not a valid title.");
                }
            }

            System.out.println("\nDo you want to borrow more books? (yes/no):");
            String moreBooks = sc.nextLine().trim().toLowerCase();
            if (moreBooks.equals("no")) break;
        }
    }

    public void returnBooks() {
        System.out.print("Enter your username: ");
        String username = sc.nextLine().trim();

        User user = registeredUsers.get(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        List<BorrowedBook> borrowedList = userBorrowedBooks.get(username);
        if (borrowedList == null || borrowedList.isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }

        System.out.println("\nBooks borrowed by " + user.getNomeCompleto() + " (" + user.getEmail() + " | CPF: " + user.getCpf() + "):");
        for (BorrowedBook book : borrowedList) {
            System.out.println("- " + book.getTitle() + " (due by " + book.getDueDate() + ")");
        }

        System.out.println("\nEnter the titles of the books you want to return, separated by commas:");
        String[] returnBooksTitles = sc.nextLine().split(",");

        Iterator<BorrowedBook> iterator = borrowedList.iterator();
        while (iterator.hasNext()) {
            BorrowedBook book = iterator.next();
            for (String title : returnBooksTitles) {
                title = title.trim();
                if (book.getTitle().equalsIgnoreCase(title)) {
                    iterator.remove();
                    books.put(title, books.getOrDefault(title, 0) + 1);
                    System.out.println("You have returned '" + title + "'. Thank you!");
                }
            }
        }

        if (borrowedList.isEmpty()) {
            userBorrowedBooks.remove(username);
        }
    }

    private boolean isValidTitle(String title) {
        return title.matches("[\\p{L}\\d ]+");
    }

    // Inner class to represent a borrowed book
    private static class BorrowedBook {
        private String title;
        private Date borrowedDate;
        private Date dueDate;

        public BorrowedBook(String title, Date borrowedDate, Date dueDate) {
            this.title = title;
            this.borrowedDate = borrowedDate;
            this.dueDate = dueDate;
        }

        public String getTitle() {
            return title;
        }

        public Date getBorrowedDate() {
            return borrowedDate;
        }

        public Date getDueDate() {
            return dueDate;
        }
    }

    public static void main(String[] args) {
        // Simulação de usuários registrados (pode ser substituído por um sistema de cadastro real)
        HashMap<String, User> registeredUsers = new HashMap<>();
        // Exemplo: registeredUsers.put("usuario1", new User(...));

        BooksManager manager = new BooksManager();
        manager.borrowBooks();
        manager.returnBooks();
    }
}
