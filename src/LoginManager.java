import java.util.HashMap;
import java.util.Scanner;

public class LoginManager {

    private HashMap<String, User> users;
    BooksManager manager = new BooksManager();

    public LoginManager(HashMap<String, User> users) {
        this.users = users;
    }

    public void handleLogin(Scanner sc) {

        System.out.println("===================================");
        System.out.println("           1. Login");
        System.out.println("           2. Forgot Password");
        System.out.println("===================================");
        System.out.print("=> Choose an option: ");

        String choice = sc.nextLine().trim();

        if ("1".equals(choice)) {
            performLogin(sc);
        } else if ("2".equals(choice)) {
            handlePasswordReset(sc);
        } else {
            System.out.println("Invalid option! Try again.");
        }

    }

    private void performLogin(Scanner sc) {

        System.out.println();
        System.out.print("Type your username: ");
        String username = sc.nextLine().trim();

        System.out.println();
        System.out.print("Type your password: ");
        String password = sc.nextLine().trim();

        if (authenticateLogin(username, password)) {

            String choiceLogin = "";
            while (!choiceLogin.equals("4")) {
                User user = users.get(username);
                System.out.println();
                System.out.println("Welcome " + user.getNomeCompleto());

                System.out.println();
                System.out.println("===================================");
                System.out.println("            1. Profile");
                System.out.println("            2. See Books");
                System.out.println("            3. Return Books");
                System.out.println("            4. Logout");
                System.out.println("===================================");
                System.out.print("=> Choose an option: ");
                choiceLogin = sc.nextLine().trim();

                switch (choiceLogin) {

                    case "1":
                        System.out.println();
                        System.out.println("----- User Data -----");
                        System.out.println("Name: " + user.getNomeCompleto());
                        System.out.println("E-mail: " + user.getEmail());
                        System.out.println("Date of birth: " + user.getDataNascimento());
                        System.out.println("Age: " + user.getIdade());
                        System.out.println("Gender: " + user.getSexo());
                        break;
                    case "2":
                        manager.borrowBooks();
                        break;
                    case "3":
                        System.out.println();
                        System.out.println("Return Books");
                        manager.returnBooks();
                    case "4":
                        System.out.println();
                        System.out.println("Logging out!");
                        break;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }

            } else{

                System.out.println();
                System.out.println("Invalid credentials! Please try again");

            }
        }


    private boolean authenticateLogin(String username, String password) {

        if (!users.containsKey(username)) {
            System.out.println("[ERROR] User '" + username + "' not found.");
            return false;
        }

        User user = users.get(username);

        System.out.println("[DEBUG] Stored password: " + user.getPassword());
        System.out.println("[DEBUG] Entered password: " + password);

        return user.getPassword().equals(password);
    }

    private void handlePasswordReset(Scanner sc) {
        System.out.println();
        System.out.print("Type your username: ");
        String username = sc.nextLine().trim();

        if (!users.containsKey(username)) {
            System.out.println("[ERROR] Username not found in system!");
            return;
        }

        User user = users.get(username);

        System.out.println();
        System.out.print("Security verification: Enter your registered email: ");
        String email = sc.nextLine().trim();

        if (user.getEmail().equals(email)) {
            System.out.println();
            System.out.print("Enter a new password: ");
            String newPassword = sc.nextLine().trim();

            user.setPassword(newPassword);

            users.put(username, user);

            System.out.println("[DEBUG] New password set successfully: " + user.getPassword());

            System.out.println("Password reset successfully! You can now log in.");
        } else {
            System.out.println("[ERROR] Incorrect email! Unable to reset password.");
        }
    }
}