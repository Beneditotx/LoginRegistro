import java.time.LocalDate;
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

    public void register(Scanner sc) {
        System.out.print("Type your username: ");
        String username = sc.nextLine().trim();

        while (username.isEmpty() || username.length() < 6 || users.containsKey(username)) {
            if (username.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid username: ");
            } else if (username.length() < 6) {
                System.out.println("Username must have at least 6 characters. Try again:");
            } else if (users.containsKey(username)) {
                System.out.println("This username is already taken! Try another one:");
            }
            System.out.print("Type your username: ");
            username = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Type your password: ");
        String password = sc.nextLine().trim();
        while (password.isEmpty() || password.length() < 6) {
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again:");
            } else if (password.length() < 6) {
                System.out.println("Password must have at least 6 characters. Try again:");
            }else if(users.containsKey(password)){
                System.out.println("This password is already taken! Try another one:");
            }
            System.out.print("Type your password: ");
            password = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Type your name: ");
        String nome = sc.nextLine().trim();
        while (!isValidName(nome)) {
            System.out.println("Invalid name. It must have at least 4 characters and cannot contain numbers or special characters.");
            System.out.print("Type your name: ");
            nome = sc.nextLine().trim();
        }


        System.out.println();
        System.out.print("Type your last name: ");
        String sobrenome = sc.nextLine().trim();
        while (!isValidName(sobrenome)) {
            System.out.println("Invalid last name. It must have at least 4 characters and cannot contain numbers or special characters.");
            System.out.print("Type your last name: ");
            sobrenome = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Type your email: ");
        String email = sc.nextLine().trim();
        while (email.isEmpty() || !email.endsWith("@gmail.com")) {
            if (email.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid email:");
            } else if (!email.endsWith("@gmail.com")) {
                System.out.println("Only @gmail.com emails are accepted. Try again:");
            }
            System.out.print("Type your email: ");
            email = sc.nextLine().trim();
        }

        System.out.println();
        System.out.print("Enter your date of birth (dd/MM/yyyy): ");
        String dataNascimentoStr = sc.nextLine().trim();
        LocalDate dataNascimento = null;
        while (true) {
            try {
                dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter your date of birth in the format dd/MM/yyyy:");
                dataNascimentoStr = sc.nextLine().trim();
            }
        }

        System.out.println();
        System.out.print("Type your age: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Age must be a whole number.");
            System.out.print("Type your age: ");
            sc.next();
        }
        int idade = sc.nextInt();
        sc.nextLine();

        while (idade < 16 || idade > 85 || !validateAgeWithBirthDate(idade, dataNascimento)) {
            if (idade < 16 || idade > 85) {
                System.out.println("Age must be between 16 and 85. Try again:");
            } else {
                System.out.println("The age does not match the birth date provided. Try again:");
            }
            System.out.print("Type your age: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Age must be a whole number.");
                System.out.print("Type your age: ");
                sc.next();
            }
            idade = sc.nextInt();
            sc.nextLine();
        }

        System.out.println();
        System.out.print("Enter your gender (M/F): ");
        String sexo = sc.nextLine().trim().toUpperCase();
        while (!sexo.equals("M") && !sexo.equals("F")) {
            System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female:");
            System.out.print("Enter your gender (M/F): ");
            sexo = sc.nextLine().trim().toUpperCase();
        }

        User user = new User(username, password, nome, sobrenome, email, dataNascimentoStr, idade, sexo);
        users.put(username, user);

        System.out.println();
        System.out.println("User registered successfully!");
    }

    private boolean validateAgeWithBirthDate(int idade, LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        int calculatedAge = today.getYear() - birthDate.getYear();
        if (today.isBefore(birthDate.plusYears(calculatedAge))) {
            calculatedAge--;
        }
        return calculatedAge == idade;
    }

    private boolean isValidName(String name) {

        String nameRegex = "^[a-zA-Z ]{4,}$";
        return Pattern.matches(nameRegex, name);
    }

}