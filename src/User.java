public class User {

    private String username;
    private String password;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNascimento;
    private int idade;
    private String sexo;

    public User(String username, String password, String nome, String sobrenome, String email, String dataNascimento, int idade, String sexo) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.sexo = sexo;
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

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getpassword(){
        return password;
    }

    public void setEmail(String email) {
    }
}