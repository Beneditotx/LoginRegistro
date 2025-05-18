import java.util.HashMap;
import java.util.Scanner;

public class LoginManager {
    private HashMap<String, User> users;
    private BooksManager booksManager;

    public LoginManager(HashMap<String, User> users, BooksManager booksManager) {
        this.users = users;
        this.booksManager = booksManager;
    }

    public void handleLogin(Scanner sc) {
        System.out.println("\n===================================");
        System.out.println("           1 - Login");
        System.out.println("           2 - Recuperar Senha");
        System.out.println("===================================");
        System.out.print("=> Escolha uma opção: ");

        String choice = sc.nextLine();

        if ("1".equals(choice)) {
            performLogin(sc);
        } else if ("2".equals(choice)) {
            handlePasswordReset(sc);
        } else {
            System.out.println("\nOpção inválida! Tente novamente.");
        }
    }

    private void performLogin(Scanner sc) {
        System.out.print("\nDigite seu username: ");
        String username = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String password = sc.nextLine();

        if (authenticateLogin(username, password)) {
            User user = users.get(username);
            showUserMenu(user, sc);
        } else {
            System.out.println("\nCredenciais inválidas! Por favor tente novamente.");
        }
    }

    private boolean authenticateLogin(String username, String password) {
        return users.containsKey(username) &&
                users.get(username).getPassword().equals(password);
    }

    private void showUserMenu(User user, Scanner sc) {
        String choiceLogin = "";
        while (!choiceLogin.equals("4")) {
            System.out.println("\nBem-vindo " + user.getNomeCompleto());
            System.out.println("===================================");
            System.out.println("            1 - Perfil");
            System.out.println("            2 - Ver Livros");
            System.out.println("            3 - Devolver Livros");
            System.out.println("            4 - Sair");
            System.out.println("===================================");
            System.out.print("=> Escolha uma opção: ");
            choiceLogin = sc.nextLine();

            switch (choiceLogin) {
                case "1":
                    showUserProfile(user);
                    break;
                case "2":
                    booksManager.borrowBooks(user);
                    break;
                case "3":
                    booksManager.borrowBooks(user);
                    break;
                case "4":
                    System.out.println("\nSaindo da conta...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
    }

    private void showUserProfile(User user) {
        System.out.println("\n----- Dados do Usuário -----");
        System.out.println("Nome: " + user.getNomeCompleto());
        System.out.println("E-mail: " + user.getEmail());
        System.out.println("Date of birth: " + user.getBirthDate());
        System.out.println("CPF: " + user.getCpf());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Phone Number: " + user.getPhone());
    }

    private void handlePasswordReset(Scanner sc) {
        System.out.print("\nDigite seu username: ");
        String username = sc.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("\nUsuário não encontrado!");
            return;
        }

        User user = users.get(username);
        System.out.print("Digite o seu Gmail atual: ");
        String currentGmail = sc.nextLine();

        if (user.getEmail().equals(currentGmail)) {
            System.out.print("Digite uma nova senha: ");
            String newPassword = sc.nextLine();
            user.setPassword(newPassword);
            System.out.println("\nSenha alterada com sucesso!");
        } else {
            System.out.println("\nGmail atual incorreto!");
        }
    }
}
