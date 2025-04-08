Documentação Técnica - Projeto LoginRegistro
1. Introdução
Este documento descreve as funcionalidades e estruturas das principais classes que compõem o projeto LoginRegistro. O sistema foi desenvolvido em Java e possui como objetivo gerenciar o login, registro e controle de livros de usuários, funcionando por meio de um menu de terminal(Futuramente os dados vão ser guardados em um banco de dados).
2. Classe Main
A classe Main é o ponto de entrada da aplicação. Ela apresenta um menu com opções de login, registro, exibição de livros e saída do sistema. Utiliza a classe Scanner para entrada de dados e interage com as classes LoginManager, RegisterManager e BooksManager para executar as ações correspondentes à escolha do usuário.
3. Classe LoginManager
Gerencia o processo de login dos usuários. Contém um método `performLogin` que o username e a senha do usuário e os compara com os dados cadastrados na Classe RegisterManager. Caso os dados estejam corretos, o login é bem-sucedido. Contendo também a funcionalidade de trocar a senha caso o usuário tenha esquecido, por meio do método handlePasswordReset.
4. Classe RegisterManager
Responsável por registrar novos usuários no sistema. O método `register()` solicita o usuário, senha, nome, sobrenome, e-mail, data de nascimento, idade e sexualidade, e armazena essas informações para uso posterior. Garante que o e-mail, usuário ainda não tenha sido cadastrado.
5. Classe BooksManager
Gerencia a exibição de livros disponíveis. A classe possui um método `displayBooks()` que lista uma coleção de livros previamente definida e armazenada internamente. Pode ser usada para simular uma biblioteca virtual.
6. Classe User
Representa o modelo de dados de um usuário. Contém atributos como nome, sobrenome, e-mail, senha, usuário, data de nascimento, idade e sexo, além dos respectivos métodos getters e setters. Serve como estrutura base para criar e manipular dados de usuários no sistema.
7. Conclusão
O projeto LoginRegistro é uma aplicação modular que permite praticar conceitos de entrada e saída de dados, manipulação de arquivos, estruturação de classes e controle de fluxo em Java. Pode ser expandido para incluir novos recursos como edição de perfil e integração com banco de dados.
