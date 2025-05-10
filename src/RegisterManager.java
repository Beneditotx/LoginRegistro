import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterManager {

    private HashMap<String, User> users;

    public RegisterManager(HashMap<String, User> users) {
        this.users = users;
    }

    public void registerUser(Scanner sc) {
        System.out.print("Enter full name: ");
        String fullName = sc.nextLine().trim();
        while (!isValidName(fullName)) {
            System.out.println("Invalid name. It must have at least 4 letters and contain no numbers or special characters.");
            System.out.print("Enter full name: ");
            fullName = sc.nextLine().trim();
        }

        String[] nameParts = fullName.split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        System.out.println();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine().trim();
        while (!phone.matches("^\\d{8,15}$")) {
            System.out.println("Invalid phone number. It must contain only digits and be between 8 and 15 characters long.");
            System.out.print("Enter phone number: ");
            phone = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();
        while (!email.endsWith("@gmail.com")) {
            System.out.println("Only @gmail.com emails are accepted. Please try again.");
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
        }


        System.out.println();
        System.out.print("Enter address: ");
        String address = sc.nextLine().trim();
        while (!isValidAddress(address)) {
        System.out.println("Invalid address. It must be at least 5 characters long and contain both letters and numbers.");
        System.out.print("Enter address: ");
        address = sc.nextLine().trim();
        }


        System.out.println();
        System.out.print("Enter date of birth (dd/MM/yyyy): ");
        String birthDateStr = sc.nextLine().trim();
        LocalDate birthDate = null;
        System.out.println();
        int age = 0;
        while (true) {
            try {
                birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                age = Period.between(birthDate, LocalDate.now()).getYears();
                if (age < 16 || age > 85) {
                    System.out.println("Invalid age. User must be between 16 and 85 years old.");
                    System.out.print("Enter date of birth (dd/MM/yyyy): ");
                    birthDateStr = sc.nextLine().trim();
                    System.out.println();
                    continue;
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use dd/MM/yyyy:");
                birthDateStr = sc.nextLine().trim();
            }
        }

        System.out.println();
        System.out.print("Enter CPF (only numbers): ");
        String cpf = sc.nextLine().trim();
        while (!cpf.matches("^\\d{11}$")) {
            System.out.println("Invalid CPF. It must contain exactly 11 digits.");
            System.out.print("Enter CPF: ");
            cpf = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Choose a username: ");
        String username = sc.nextLine().trim();
        while (username.isEmpty() || users.containsKey(username)) {
            System.out.println("Invalid or already existing username. Please choose another:");
            System.out.print("Choose a username: ");
            username = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Choose a password: ");
        String password = sc.nextLine().trim();
        while (password.length() < 6) {
            System.out.println("Password too short. It must be at least 6 characters long.");
            System.out.print("Choose a password: ");
            password = sc.nextLine().trim();
        }

        User user = new User(username, password, firstName, lastName, email, birthDateStr, phone, address, cpf, age);
        users.put(username, user);

        System.out.println("\nUser successfully registered!");
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
