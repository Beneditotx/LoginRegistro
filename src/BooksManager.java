import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class BooksManager {
    private Map<String, Book> availableBooks;
    private Scanner scanner;
    private static final int LOAN_DAYS = 14;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BooksManager() {
        this.availableBooks = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
        initializeBooks();
    }

    private void initializeBooks() {

        availableBooks.put("001", new Book("Dom Quixote", "Clássicos da Literatura", "Miguel de Cervantes"));
        availableBooks.put("002", new Book("1984", "Clássicos da Literatura", "George Orwell"));
        availableBooks.put("003", new Book("O Pequeno Príncipe", "Clássicos da Literatura", "Antoine de Saint-Exupéry"));
        availableBooks.put("004", new Book("Orgulho e Preconceito", "Clássicos da Literatura", "Jane Austen"));

        availableBooks.put("101", new Book("Duna", "Ficção Científica", "Frank Herbert"));
        availableBooks.put("102", new Book("Fundação", "Ficção Científica", "Isaac Asimov"));
        availableBooks.put("103", new Book("Neuromancer", "Ficção Científica", "William Gibson"));

        availableBooks.put("201", new Book("O Senhor dos Anéis", "Fantasia", "J.R.R. Tolkien"));
        availableBooks.put("202", new Book("Harry Potter e a Pedra Filosofal", "Fantasia", "J.K. Rowling"));
        availableBooks.put("203", new Book("As Crônicas de Nárnia", "Fantasia", "C.S. Lewis"));

        availableBooks.put("301", new Book("Cem Anos de Solidão", "Romance", "Gabriel García Márquez"));
        availableBooks.put("302", new Book("O Morro dos Ventos Uivantes", "Romance", "Emily Brontë"));

        availableBooks.put("401", new Book("O Código Da Vinci", "Mistério/Suspense", "Dan Brown"));
        availableBooks.put("402", new Book("E Não Sobrou Nenhum", "Mistério/Suspense", "Agatha Christie"));

        availableBooks.put("501", new Book("Sapiens: Uma Breve História da Humanidade", "Não-Ficção", "Yuval Noah Harari"));
    }

    public void showCategorySelection() {
        Set<String> categories = new HashSet<>();

        for (Book book : availableBooks.values()) {
            categories.add(book.getCategory());
        }

        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        int index = 1;
        for (String category : categories) {
            System.out.println(index + " - " + category);
            index++;
        }
        System.out.println("0 - Voltar");
        System.out.println("99 - Mostrar todos os livros");

        System.out.print("\nEscolha uma categoria: ");
        String choice = scanner.nextLine();

        if (choice.equals("0")) {
            return;
        } else if (choice.equals("99")) {
            listAvailableBooks();
            return;
        }

        try {
            int categoryIndex = Integer.parseInt(choice);
            if (categoryIndex > 0 && categoryIndex <= categories.size()) {
                String selectedCategory = categories.toArray(new String[0])[categoryIndex - 1];
                listBooksByCategory(selectedCategory);
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
        }
    }

    private void listBooksByCategory(String category) {
        System.out.println("\n=== " + category.toUpperCase() + " ===");
        boolean found = false;

        for (Map.Entry<String, Book> entry : availableBooks.entrySet()) {
            if (entry.getValue().getCategory().equals(category)) {
                System.out.println("Código: " + entry.getKey() +
                        " | Título: " + entry.getValue().getTitle() +
                        " | Autor: " + entry.getValue().getAuthor());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nenhum livro encontrado nesta categoria.");
        }
    }

    private void listAvailableBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("Não há livros disponíveis no momento.");
            return;
        }

        String currentCategory = "";
        for (Map.Entry<String, Book> entry : availableBooks.entrySet()) {
            String category = entry.getValue().getCategory();
            if (!category.equals(currentCategory)) {
                System.out.println("\n=== " + category.toUpperCase() + " ===");
                currentCategory = category;
            }
            System.out.println("Código: " + entry.getKey() +
                    " | Título: " + entry.getValue().getTitle() +
                    " | Autor: " + entry.getValue().getAuthor());
        }
    }

    public void borrowBooks(User user) {
        while (true) {
            System.out.println("\n=== MENU DE LIVROS ===");
            System.out.println("1 - Ver todos os livros");
            System.out.println("2 - Escolher por categoria");
            System.out.println("0 - Voltar ao menu anterior");

            System.out.print("\nEscolha uma opção: ");
            String option = scanner.nextLine();

            if (option.equals("0")) {
                return;
            } else if (option.equals("1")) {
                listAvailableBooks();
            } else if (option.equals("2")) {
                showCategorySelection();
                continue;
            } else {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.print("\nDigite o código do livro ou 0 para voltar: ");
            String codigo = scanner.nextLine();

            if (codigo.equals("0")) {
                continue;
            }

            if (availableBooks.containsKey(codigo)) {
                Book book = availableBooks.get(codigo);
                LocalDate dataRetirada = LocalDate.now();
                LocalDate dataDevolucao = dataRetirada.plusDays(LOAN_DAYS);

                user.borrowBook(codigo, book.getTitle(), dataRetirada, dataDevolucao);
                availableBooks.remove(codigo);

                System.out.println("\nLivro '" + book.getTitle() + "' emprestado com sucesso!");
                System.out.println("Categoria: " + book.getCategory());
                System.out.println("Autor: " + book.getAuthor());
                System.out.println("Data de retirada: " + dataRetirada.format(DATE_FORMATTER));
                System.out.println("Data de devolução: " + dataDevolucao.format(DATE_FORMATTER));
                return;
            } else {
                System.out.println("\nCódigo inválido! Tente novamente.");
            }
        }
    }

    private static class Book {
        private String title;
        private String category;
        private String author;

        public Book(String title, String category, String author) {
            this.title = title;
            this.category = category;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public String getAuthor() {
            return author;
        }
    }
}