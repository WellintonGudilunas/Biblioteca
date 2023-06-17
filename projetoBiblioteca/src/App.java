import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.Biblioteca;
import model.Emprestimo;
import controller.Salvar;
import model.Livro;
import model.Usuario;

public abstract class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        try {
            Biblioteca biblioteca = new Biblioteca();
            
            try {
                Livro l1 = new Livro("A bela e a Fera", "Paulera", 2012, "Romance", 10, 1);
                Livro l2 = new Livro("Clean Code", "Jeff Espeto", 2003, "De programa", 1, 2);
                Livro l3 = new Livro("Clean house", "Jeff do Bigode", 2003, "De casa", 0, 3);
                biblioteca.cadastrarLivro(l1);
                biblioteca.cadastrarLivro(l2);
                biblioteca.cadastrarLivro(l3);
                
                Usuario u1 = new Usuario(555, "Paulo", "paulo", "paulo", "paulo", "paulo", "paulo");
                Usuario u2 = new Usuario(666, "Tiago", "Tiago", "Tiago", "Tiago", "Tiago", "Tiago");
                biblioteca.cadastrarUsuario(u1);
                biblioteca.cadastrarUsuario(u2);
                
                Emprestimo emprestimoTeste = new Emprestimo(l1, u1, 10);
                biblioteca.emprestarLivro(emprestimoTeste);
            } catch (Exception e) {
                System.out.println("Erro em cadastrar dados iniciais de TESTE" + e);
            }


            Salvar salvar = new Salvar();
            salvar.exportarDados();
            try {
                Boolean menu = true;
                while(menu){
                    System.out.println(
                        "\n\nBIBLIOTECA!!! \n\n" +
                        "1- Cadastrar Livros \n" +
                        "2- Cadastrar Usuários \n" +
                        "3- Emprestar Livros \n" +
                        "4- Devolver Livros \n" +
                        "5- Buscar Livro \n" +
                        "6- Buscar Usuario \n" +
                        "7- Log livros \n" +
                        "8- Log usuários \n" +
                        "9- Log Empréstimos \n" +
                        "\nRELATÓRIOS \n" +
                        "200- Listar livros (tempo Excecução)\n" +
                        "201- Listar usuários (tempo Excecução)\n" +
                        "202- Listar empréstimos (tempo Excecução)\n" +
                        "203- Listar TODOS livros Emprestados \n" +
                        "204- Listar Livros Populares \n" + 
                        "205- Lista empréstimos em atraso \n" +
                        "206- (bonus) Usuarios com emprestimo em andamento \n" +
                        "0- Sair"
                        );
                    System.out.print("Digite a opção desejada: ");

                    int opcao;
                    try {
                        opcao = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n----------------------- \nVocê deve digitar um número !!!\n -----------------------");
                        scanner.next();
                        continue;
                    }
                                                
                   

                    if(opcao == 0) { // FOI
                        return;
                    } else if (opcao == 1){ // FOI
                        try {
                            System.out.println("\nCadastrar Livros \n");

                            System.out.print("Digite o código do livro: ");
                            scanner.nextLine();
                            int codigo = scanner.nextInt();

                            System.out.print("Digite o titulo do livro: ");
                            scanner.nextLine();
                            String titulo = scanner.nextLine();

                            System.out.print("Digite o autor do livro: ");
                            String autor = scanner.nextLine();
                            
                            System.out.print("Digite a categoria do livro: "); 
                            String categoria = scanner.nextLine();

                            System.out.print("Digite o ano de publicação do livro: ");
                            int anoPublicacao = scanner.nextInt();

                            
                            System.out.print("Digite a quantidade disponivel do livro: ");
                            int quantidadeDisponivel = scanner.nextInt();

                            Livro livro = new Livro(titulo, autor, anoPublicacao, categoria, quantidadeDisponivel, codigo);
                            biblioteca.cadastrarLivro(livro);

                            salvar.adicionarLogLivro("Livro cadastrado em ", LocalDateTime.now(), livro);
                            salvar.salvarLogLivro("src/model/logs/logLivro.ser");

                            System.out.println("\n-----------------------\nLivro cadastrado!!!\n -----------------------");
                        } catch (InputMismatchException e) {
                            System.out.println("\nEntrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }
                    } else if (opcao == 2){ // FOI
                        try {
                            
                            System.out.println("\nCadastrar Usuários \n");

                            System.out.print("Digite o código do Usuário: ");
                            int codigo = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Digite o nome do Usuário: ");

                            String nome = scanner.nextLine();

                            System.out.print("Digite o sobrenome do Usuário: ");
                            String sobrenome = scanner.nextLine();

                            System.out.print("Digite o endereco do Usuário: ");
                            String endereco = scanner.nextLine();

                            System.out.print("Digite o email do Usuário: ");
                            String email = scanner.nextLine();

                            System.out.print("Digite o cpf do Usuário: ");
                            String cpf = scanner.nextLine();

                            System.out.print("Digite o telefone do Usuário: ");
                            String telefone = scanner.nextLine();

                            Usuario user = new Usuario(codigo, nome, sobrenome, endereco, email, cpf, telefone);
                            biblioteca.cadastrarUsuario(user);
                            salvar.adicionarLogUsuario("Usuario cadastrado em ", LocalDateTime.now(), user);
                            
                            salvar.salvarLogUsuario("src/model/logs/logUser.ser");
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }
                    } else if (opcao == 3){ // FOI
                        try {
                            System.out.println("\nEmpréstimo de livros\n");
                            System.out.print("Digite o código do empréstimo: ");
                            int codigoEmprestimo = scanner.nextInt();
                            
                            System.out.print("Digite o código do usuário que vai emprestar o livro: ");
                            int codigoUsuario = scanner.nextInt();
                            
                            System.out.print("Digite o código do livro que deseja emprestar: ");
                            int codigoLivro = scanner.nextInt();
                            
                            Emprestimo emprestimo = new Emprestimo(biblioteca.pesquisarLivroCodigo2(codigoLivro), biblioteca.pesquisarUsuarioCodigo2(codigoUsuario), codigoEmprestimo);
                            
                            if (biblioteca.emprestarLivro(emprestimo)){
                                LocalDateTime data = LocalDateTime.now();
                                salvar.adicionarLogEmprestimo("Novo empréstimo cadastrado em ", data, emprestimo);
                                salvar.salvarLogEmprestimo("src/model/logs/logEmprestimo.ser");
                            } else {
                                System.out.println("Erro no empréstimo");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }
                    } else if (opcao == 4){ // FOI
                        try {
                            // DEVOLVER LIVROS
                            System.out.println("\nDevolução de livros\n");
                            System.out.println("Digite o código do Empréstimo que você deseja devolver");
                            int codigoEmprestimo = scanner.nextInt();
                            
                            LocalDateTime data = LocalDateTime.now();
                            salvar.adicionarLogEmprestimo("Nova DEVOLUÇÃO no Emprestimo com ", data, biblioteca.pesquisarEmprestimoCodigo2(codigoEmprestimo));
                            if (biblioteca.devolverLivro(codigoEmprestimo)){
                                salvar.salvarLogEmprestimo("src/model/logs/logEmprestimo.ser");
                            } else {
                                System.out.println("Erro na devolução");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }
                        
                    } else if (opcao == 5){ // FOI
                        try {
                            System.out.println("\nBUSCAR LIVRO\n");
                            int filtroInt;
                            String filtroString;
                            System.out.print("Você deseja buscar um livro por código, titulo ou categoria?");
                            scanner.nextLine();
                            String buscarPor = scanner.nextLine().toUpperCase();

                            if(buscarPor.equals("CODIGO")){
                                System.out.print("Digite o código do livro: ");
                                filtroInt = scanner.nextInt();
                                biblioteca.pesquisarLivroCodigo(filtroInt);
                                
                            } else if (buscarPor.equals("TITULO")){
                                System.out.print("Digite o Título do livro: ");
                                filtroString = scanner.nextLine();
                                biblioteca.pesquisarLivroTitulo(filtroString);
                                
                            } else if (buscarPor.equals("CATEGORIA")){
                                System.out.print("Digite a Categoria do livro: ");
                                filtroString = scanner.nextLine();
                                biblioteca.pesquisarLivroCategoria(filtroString);
                                
                            } else {
                                System.out.println("Não é possivel buscar um livro por " + buscarPor);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }

                    } else if (opcao == 6){ // FOI
                        try {
                            System.out.println("\nBUSCAR USUARIO\n");
                            int filtroInt;
                            String filtroString;
                            System.out.print("Você deseja buscar um USUARIO por código ou nome?");
                            scanner.nextLine();
                            String buscarPor = scanner.nextLine().toUpperCase();
                            
                            if(buscarPor.equals("CODIGO")){
                                System.out.print("Digite o código do USUARIO: ");
                                filtroInt = scanner.nextInt();
                                biblioteca.pesquisarUsuarioCodigo(filtroInt);
                                
                            } else if (buscarPor.equals("NOME")){
                                System.out.print("Digite o nome do USUARIO: ");
                                filtroString = scanner.nextLine();
                                biblioteca.pesquisarUsuarioNome(filtroString);
                            } else {
                                System.out.println("Não é possivel buscar um USUARIO por " + buscarPor);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada de dados inválida: " + e);
                            scanner.next();
                            continue;
                        } catch (Exception e){
                            System.out.println("Erro inesperado: " + e);
                        }

                    } else if (opcao == 7){ // FOI
                        try {
                            biblioteca.lerLogsLivro();
                        } catch (Exception e){
                            System.out.println("Erro ao ler logs de Livro: " + e);
                        }
                    } else if (opcao == 8){ // FOI
                        try {
                            biblioteca.lerLogsUsuario();
                        } catch (Exception e){
                            System.out.println("Erro ao ler logs de Usuario: " + e);
                        }
                    } else if (opcao == 9){ // FOI
                        try {
                            biblioteca.lerLogsEmprestimo();
                        } catch (Exception e){
                            System.out.println("Erro ao ler logs de Emprestimo: " + e);
                        }
                    } else if (opcao == 200){ // FOI
                        try {
                            System.out.println("\n200- Listar livros \n");
                            biblioteca.listarTodosLivros();
                        }catch (Exception e){
                            System.out.println("Erro ao listar os livros (Opção 200 menu): " + e);
                        }
                    } else if (opcao == 201){ // FOI
                        try {
                            System.out.println("\n201- Listar usuários \n");
                            biblioteca.listarTodosUsuarios();
                        } catch (Exception e){
                            System.out.println("Erro ao listar usuarios (Opção 201 menu): " + e);
                        }
                    } else if (opcao == 202){ // FOI
                        try {
                            System.out.println("\n202- Listar empréstimos \n");
                            biblioteca.listarTodosEmprestimos();
                        } catch (Exception e){
                            System.out.println("Erro ao listar emprestimos (Opção 202 menu): " + e);
                        }
                    } else if (opcao == 203){ // FOI
                        try {
                            System.out.println("\n203- Listar Todos os livros que já foram emprestados \n");
                            biblioteca.getListarLivrosEmprestados();
                        } catch (Exception e){
                            System.out.println("Erro ao listar Livros emprestados (Opção 203 menu): " + e);
                        }
                    } else if (opcao == 204){ // FOI
                        try {
                            System.out.println("\n204- Listar livros populares (Emprestados mais de 3 vezes) \n");
                            biblioteca.getLivrosPopulares();
                        } catch (Exception e){
                            System.out.println("Erro ao Listar livros populares (Opção 204 menu): " + e);
                        }

                    } else if (opcao == 205){ // FOI
                        try {
                            System.out.println("\nEmpréstimos em atraso");
                            biblioteca.getEmprestimosAtrasados();
                        } catch (Exception e){
                            System.out.println("Erro ao Listar empréstimos em atraso (Opção 205 menu): " + e);
                        }

                    } else if (opcao == 206){ // FOI
                        try {
                            System.out.println("\nUsuários que tem empréstimo em andamento");
                            biblioteca.getUsuarioEmprestimoEmAndamento();
                        } catch (Exception e){
                            System.out.println("Erro ao Usuarios com empréstimo em andamento (Opção 206 menu): " + e);
                        }

                    } else {
                        System.out.println("\n----------------------- \nOpção inválida!!\n -----------------------");
                    }   
                }
            } catch (Exception e) {
                System.out.println("Erro no menu: " + e);
            }
        } catch (Exception e) {
            System.out.println("DEU RUIM: " + e);
        }
    }
}
