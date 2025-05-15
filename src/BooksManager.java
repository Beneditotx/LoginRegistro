import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BooksManager {
    private Map<String, String> availableBooks;
    private Scanner scanner;

    public BooksManager() {
        this.availableBooks = new HashMap<>();
        this.scanner = new Scanner(System.in);
        initializeBooks();
    }

    private void initializeBooks() {
        availableBooks.put("001", "Dom Quixote");
        availableBooks.put("002", "1984");
        availableBooks.put("003", "O Pequeno Príncipe");
    }

    public void borrowBooks(User user) {
        while (true) {
            System.out.println("\n=== LIVROS DISPONÍVEIS ===");
            listAvailableBooks();
            System.out.println("0 - Voltar ao menu anterior");

            System.out.print("\nDigite o código do livro ou 0 para voltar: ");
            String codigo = scanner.nextLine();

            if (codigo.equals("0")) {
                return;
            }

            if (availableBooks.containsKey(codigo)) {
                String titulo = availableBooks.get(codigo);
                user.borrowBook(codigo, titulo);
                availableBooks.remove(codigo);
                System.out.println("\nLivro '" + titulo + "' emprestado com sucesso!");
                return;
            } else {
                System.out.println("\nCódigo inválido! Tente novamente.");
            }
        }
    }

    public void returnBooks(User user) {
        while (true) {
            System.out.println("\n=== SEUS LIVROS EMPRESTADOS ===");
            if (user.getBorrowedBooks().isEmpty()) {
                System.out.println("Você não tem livros emprestados.");
                return;
            }

            user.listBorrowedBooks();
            System.out.println("0 - Voltar ao menu anterior");

            System.out.print("\nDigite o código do livro para devolver ou 0 para voltar: ");
            String codigo = scanner.nextLine();

            if (codigo.equals("0")) {
                return;
            }

            Map<String, String> borrowedBooks = user.getBorrowedBooks();
            if (borrowedBooks.containsKey(codigo)) {
                String titulo = borrowedBooks.get(codigo);  // Obtém o título ANTES de remover
                user.returnBook(codigo);                   // Remove o livro do usuário
                availableBooks.put(codigo, titulo);        // Devolve para a biblioteca
                System.out.println("\nLivro '" + titulo + "' devolvido com sucesso!");
                return;
            } else {
                System.out.println("\nVocê não possui um livro com este código!");
            }
        }
    }

    private void listAvailableBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("Não há livros disponíveis no momento.");
            return;
        }

        availableBooks.forEach((codigo, titulo) -> {
            System.out.println("Código: " + codigo + " | Título: " + titulo);
        });
    }
}
