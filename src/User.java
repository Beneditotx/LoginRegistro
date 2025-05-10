public class User {

    private String username;
    private String password;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNascimento;
    private String telefone;
    private String endereco;
    private String cpf;
    private int idade;

    public User(String username, String password, String nome, String sobrenome, String email,
                String dataNascimento, String telefone, String endereco, String cpf, int idade) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void put(String cpf, User user) {

    }
}
