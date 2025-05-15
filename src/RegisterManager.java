import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterManager {
    private HashMap<String, User> users;
    private Scanner scanner;

    public RegisterManager(HashMap<String, User> users) {
        this.users = users;
        this.scanner = new Scanner(System.in);
    }

    public void registerUser() {
        String fullName = getValidFullName();
        String[] nameParts = fullName.split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        String phone = getValidPhone();
        String email = getValidEmail();
        String address = getValidAddress();
        String birthDateStr = getValidBirthDate();
        String cpf = getValidCPF();
        String username = getValidUsername();
        String password = getValidPassword();

        User user = new User(username, password, firstName, lastName, email, birthDateStr, phone, address, cpf, calculateAge(birthDateStr));
        users.put(username, user);

        System.out.println("\nUser successfully registered!");
    }

    private String getValidFullName() {
        while (true) {
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine().trim();
            if (isValidName(fullName)) {
                return fullName;
            }
            System.out.println("Invalid name. It must have at least 4 letters and contain no numbers or special characters.");
        }
    }

    private String getValidPhone() {
        while (true) {
            System.out.print("\nEnter phone number: ");
            String phone = scanner.nextLine().trim();
            if (phone.matches("^\\d{8,15}$")) {
                return phone;
            }
            System.out.println("Invalid phone number. It must contain only digits and be between 8 and 15 characters long.");
        }
    }

    private String getValidEmail() {
        while (true) {
            System.out.print("\nEnter email: ");
            String email = scanner.nextLine().trim();
            if (email.endsWith("@gmail.com")) {
                return email;
            }
            System.out.println("Only @gmail.com emails are accepted. Please try again.");
        }
    }

    private String getValidAddress() {
        while (true) {
            System.out.print("\nEnter address: ");
            String address = scanner.nextLine().trim();
            if (isValidAddress(address)) {
                return address;
            }
            System.out.println("Invalid address. It must be at least 5 characters long and contain both letters and numbers.");
        }
    }

    private String getValidBirthDate() {
        while (true) {
            System.out.print("\nEnter date of birth (dd/MM/yyyy): ");
            String birthDateStr = scanner.nextLine().trim();
            try {
                LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                if (age >= 16 && age <= 85) {
                    return birthDateStr;
                }
                System.out.println("Invalid age. User must be between 16 and 85 years old.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use dd/MM/yyyy.");
            }
        }
    }

    private String getValidCPF() {
        while (true) {
            System.out.print("\nEnter CPF (only numbers): ");
            String cpf = scanner.nextLine().trim();
            if (cpf.matches("^\\d{11}$")) {
                return cpf;
            }
            System.out.println("Invalid CPF. It must contain exactly 11 digits.");
        }
    }

    private String getValidUsername() {
        while (true) {
            System.out.print("\nChoose a username: ");
            String username = scanner.nextLine().trim();
            if (!username.isEmpty() && !users.containsKey(username)) {
                return username;
            }
            System.out.println("Invalid or already existing username. Please choose another.");
        }
    }

    private String getValidPassword() {
        while (true) {
            System.out.print("\nChoose a password: ");
            String password = scanner.nextLine().trim();
            if (password.length() >= 6) {
                return password;
            }
            System.out.println("Password too short. It must be at least 6 characters long.");
        }
    }

    private int calculateAge(String birthDateStr) {
        LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private boolean isValidName(String name) {
        return Pattern.matches("^[a-zA-ZÀ-ÿ\\s]{4,}$", name);
    }

    private boolean isValidAddress(String address) {
        return address.length() >= 5 &&
                address.matches(".*[a-zA-Z].*") &&
                address.matches(".*\\d.*");
    }
}