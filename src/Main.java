import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<String, User> users = new HashMap<>();

        RegisterManager registerManager = new RegisterManager(users);
        LoginManager loginManager = new LoginManager(users);


        Scanner sc = new Scanner(System.in);
        String option;

        do {

            System.out.println();
            System.out.println("===================================");
            System.out.println("            MAIN MENU          ");
            System.out.println("===================================");
            System.out.println("           1 - Login");
            System.out.println("           2 - Register");
            System.out.println("           3 - Exit");
            System.out.println("===================================");

            System.out.print("=> Chose your option: ");
            option = sc.nextLine();
            System.out.println();

            switch (option) {
                case "1":
                    loginManager.handleLogin(sc);
                    break;
                case "2":
                    registerManager.registerUser(sc);
                    break;
                case "3":
                    System.out.println();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println();
                    System.out.println("Option invalid! Try Again.");
            }

        } while (!option.equals("3"));

        sc.close();
    }
}