import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String phone;
    private String address;
    private String cpf;
    private int age;
    private Map<String, String> borrowedBooks;

    public User(String username, String password, String firstName, String lastName,
                String email, String birthDate, String phone, String address,
                String cpf, int age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.cpf = cpf;
        this.age = age;
        this.borrowedBooks = new HashMap<>();
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getBirthDate() { return birthDate; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getCpf() { return cpf; }
    public int getAge() { return age; }

    // Método adicionado para resolver o problema
    public String getNomeCompleto() {
        return firstName + " " + lastName;
    }

    // Setters
    public void setPassword(String password) { this.password = password; }

    // Book management methods
    public void borrowBook(String code, String title) {
        borrowedBooks.put(code, title);
    }

    public boolean returnBook(String code) {
        return borrowedBooks.remove(code) != null;
    }

    public Map<String, String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void listBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Você não tem livros emprestados.");
            return;
        }

        borrowedBooks.forEach((code, title) -> {
            System.out.println("Código: " + code + " | Título: " + title);
        });
    }
}
