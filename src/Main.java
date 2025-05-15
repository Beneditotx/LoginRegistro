import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, User> users = new HashMap<>();
        BooksManager booksManager = new BooksManager();
        RegisterManager registerManager = new RegisterManager(users);
        LoginManager loginManager = new LoginManager(users, booksManager);

        Scanner sc = new Scanner(System.in);
        String option;

        do {
            System.out.println("\n===================================");
            System.out.println("            MENU PRINCIPAL          ");
            System.out.println("===================================");
            System.out.println("           1 - Login");
            System.out.println("           2 - Registrar");
            System.out.println("           3 - Sair");
            System.out.println("===================================");
            System.out.print("=> Escolha uma opção: ");

            option = sc.nextLine();

            switch (option) {
                case "1":
                    loginManager.handleLogin(sc);
                    break;
                case "2":
                    registerManager.registerUser();
                    break;
                case "3":
                    System.out.println("\nSaindo do sistema...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (!option.equals("3"));

        sc.close();
    }
}
