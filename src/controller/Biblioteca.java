package controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Emprestimo;
import model.Livro;
import model.Log;
import model.LogEmprestimo;
import model.LogLivro;
import model.LogUsuario;
import model.Usuario;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<Livro>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    protected Salvar salvar; // SE COLOCAR PRIVATE FICA COM WARNING POR CAUSA QUE SÓ USA ISSO NO APP
    
    public Biblioteca() {
        this.salvar = new Salvar();
    }

    // EMPRESTAR LIVRO ---------------------------------------
    public boolean emprestarLivro(Emprestimo emprestimo){ // PRONTO
        try {
            Usuario usuario = emprestimo.getUsuario();
            Livro livroEmprestar = emprestimo.getLivro();

        if(usuario == null){
                System.out.println("Usuário não encontrado");
                return false;
        }
        if(livroEmprestar == null){
            System.out.println("Livro não encontrado");
            return false;
        }
        if(usuario.temLivroEmprestado() == true){
                System.out.println("O usuário já tem um livro emprestado");
                return false;
        }
        if(livroEmprestar.getQuantidadeDisponivel() > 0){
                livroEmprestar.setQuantidadeDisponivel(livroEmprestar.getQuantidadeDisponivel() - 1);
                livroEmprestar.setQuantidadeVezesEmprestado(livroEmprestar.getQuantidadeVezesEmprestado() + 1);
                usuario.setLivroEmprestado(true);
                this.emprestimos.add(emprestimo);
            } else {
                System.out.println("Não há livro em estoq");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao emprestar Livro" + e);
            return false;
        }
    }

    //----------------------------------------------------------

    // DEVOLVER LIVRO 
    public boolean devolverLivro(int codigoEmprestimo){ // PRONTO
        try {
            boolean encontrouEmprestimo = false;
            for (Emprestimo emprestimo : this.emprestimos) {
                if(emprestimo.getCodigoEmprestimo() == codigoEmprestimo){
                    emprestimo.getUsuario().setLivroEmprestado(false);
                    emprestimo.getLivro().setQuantidadeDisponivel(emprestimo.getLivro().getQuantidadeDisponivel() + 1);
                    emprestimo.setDataDevolucaoEfetiva(LocalDateTime.now());
                    this.emprestimos.remove(emprestimo);
                    encontrouEmprestimo = true;
                    return true;
                }
            }
            if(!encontrouEmprestimo){
                System.out.println("Não encontrou empréstimo");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao devolver livro: " + e);
            return false;
        }
        return false;
    }

    //--------------------------------------------------------------------

    // CADASTRAR LIVRO / LIVROS --------------------------------------------

    public void cadastrarLivro (Livro livro){ // PRONTO
        try {
            if(livro != null){
                this.livros.add(livro);
            } else {
                System.out.println("Você não pode cadastrar um livro vazio");
            }
        } catch (Exception e) {
            System.out.println("Erro em cadastrar livro: " + e);
        }
    }

    public void cadastrarLivro (List<Livro> livrosParaCadastrar){ // PRONTO
        try {
            if(livrosParaCadastrar.size() > 0){
                for (Livro livro: livrosParaCadastrar) {
                    this.livros.add(livro);
                }
            } else {
                System.out.println("Você não pode cadastrar uma lista de Livros vazia");
            }
        } catch (Exception e) {
            System.out.println("Erro em cadastrar Lista de livros" + e);
        }
    }

    //----------------------------------------------------------------------------

    // CADASTRAR USUARIO / USUARIOS --------------------------------------------
    public void cadastrarUsuario (Usuario user){ // PRONTO
        try {
            if(user != null){
                this.usuarios.add(user);
            } else {
                System.out.println("Você não pode cadastrar um usuário vazio");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e);
        }
    }

    public void cadastrarUsuarios (List<Usuario> usuariosParaCadastrar){ // PRONTO
        try {
            if(usuariosParaCadastrar.size() > 0){
                for (Usuario user : usuariosParaCadastrar) {
                    this.usuarios.add(user);
                }
            } else {
                System.out.println("Você não pode cadastrar uma lista de Usuarios vazia");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Lista de usuários" + e);
        }
        
    }
    // -------------------------------------------------------------------------

    // PESQUISAR EMPRESTIMO POR -------------------------------------------------
    public void pesquisarEmprestimoCodigo(int codigo){ // PRONTO - PRINTA UM EMPRESTIMO
        try {
            boolean emprestimoEncontrado = false;
            if(codigo > 0){
                for (Emprestimo emprestimo : this.emprestimos) {
                    if(emprestimo.getCodigoEmprestimo() == codigo){
                        System.out.println("\n Empréstimo Encontrado!!!");
                        System.out.println(emprestimo);
                        emprestimoEncontrado = true;
                    }
                    
                }
            }
            if (!emprestimoEncontrado) {
                System.out.println("\nEmpréstimo não encontrado!!!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar Empréstimo por código: " + e);
        }
    }
    
    public Emprestimo pesquisarEmprestimoCodigo2(int codigo){ // PRONTO- RETORNA UM EMPRESTIMO ENVES DE PRINTA
        try {   
            if(codigo > 0){
                for (Emprestimo emprestimo : this.emprestimos) {
                    if(emprestimo.getCodigoEmprestimo() == codigo){
                        return emprestimo;
                    }
                    
                }
            } 
            return null;

        } catch (Exception e) {
            System.out.println("Erro em pesquisar empréstimo por código2: " + e);
            return null;
        }    
    }
    // ------------------------------------------------------------------------------


    // PESQUISAR LIVRO POR ------------------------------------------------------
    @SuppressWarnings("unchecked")// COLOQUEI ISSO PQ SE NAO FICA MARCANDO EM LARANJA UM  WARNING NO CAST DE OBJECT IN PARA LIST<LOG>
    public void pesquisarLivroCodigoLog(int codigo) { // PRONTO // REVER UTILIZACAO DO METODO
        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logLivro.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            boolean livroEncontrado = false;

            for (Log log : logs) {
                    LogLivro logLivro = (LogLivro) log;
                    if(logLivro.getLivro().getCodigo() == codigo){
                        System.out.println(logLivro.getLivro());
                        livroEncontrado = true;
                    }
            }

            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }

            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs de Livro: " + e);
        }
    }

    public void pesquisarLivroCodigo(int codigo){ // PRONTO
        try {
            boolean livroEncontrado = false;

            if(codigo > 0){
                for (Livro livro : this.livros) {
                    if(livro.getCodigo() == codigo){
                        System.out.println("\nLivro Encontrado!!!");
                        System.out.println(livro);
                        livroEncontrado = true;
                    }
                    
                }
            }
            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println("Erro em pesquisar Livro por código: " + e);
        }
    }
    
    public void pesquisarLivroTitulo(String titulo){ // PRONTO
        try {
            boolean livroEncontrado = false;

            if(titulo != null){
                for (Livro livro : this.livros) {
                    if(livro.getTitulo().toUpperCase().equals(titulo.toUpperCase())){
                        System.out.println("\nLivro Encontrado!!!");
                        System.out.println(livro);
                        livroEncontrado = true;
                    }
                    
                }
            }
            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println("Erro em pesquisar livro por titulo: " + e);
        }
    }

    public void pesquisarLivroCategoria(String categoria){ // PRONTO
        try {
            boolean livroEncontrado = false;

            if(categoria != null){
                for (Livro livro : this.livros) {
                    if(livro.getCategoria().toUpperCase().equals(categoria.toUpperCase())){
                        System.out.println("\nLivro Encontrado!!!");
                        System.out.println(livro);
                        livroEncontrado = true;
                    }
                    
                }
            }
            if (!livroEncontrado) {
                System.out.println("\nLivro não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println("Erro em pesquisar livro por categoria: " + e);
        }
    }
   
    public Livro pesquisarLivroCodigo2(int codigo) { // PRONTO -  RETORNA UM LIVRO ENVÉS DE PRINTAR UM LIVRO
        try {   
            if(codigo > 0){
                for (Livro livro : this.livros) {
                    if(livro.getCodigo() == codigo){
                        return livro;
                    }
                    
                }
            } 
            return null;

        } catch (Exception e) {
            System.out.println("Erro em pesquisar Livro por código2: " + e);
            return null;
        }    
    }
    // ------------------------------------------------------------------------------
   
    //PESQUISAR USUARIO POR -----------------------------------------------------
    @SuppressWarnings("unchecked")
    public void pesquisarUsuarioCodigoLog(int codigo){ // PRONTO // REVER UTILIZAÇÃO DO MÉTODO

        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logUser.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            boolean usuarioEncontrado = false;

            for (Log log : logs) {
                    LogUsuario logUsuario = (LogUsuario) log;
                    if(logUsuario.getUsuario().getCodigo() == codigo){
                        System.out.println(logUsuario.getUsuario());
                        usuarioEncontrado = true;
                        break;
                    }
            }

            if (!usuarioEncontrado) {
                System.out.println("\nUsuário não encontrado!!!");
            }

            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs de usuário: " + e);
        }
    }

    public void pesquisarUsuarioCodigo(int codigo){ // PRONTO
        try {
            boolean usuarioEncontrado = false;

            if(codigo > 0){
                for (Usuario usuario : this.usuarios) {
                    if(usuario.getCodigo() == codigo){
                        System.out.println("\nUsuario Encontrado!!!");
                        System.out.println(usuario);
                        usuarioEncontrado = true;
                    }
                    
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("\nUsuário não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println("Erro em pesquisar usuário por código: " + e);
        }
    }

    public void pesquisarUsuarioNome(String nome){ // PRONTO
        try {
            boolean usuarioEncontrado = false;

            if(nome != null){
                for (Usuario usuario : this.usuarios) {
                    if(usuario.getNome().toUpperCase().equals(nome.toUpperCase())){
                        System.out.println("\nUsuario Encontrado!!!");
                        System.out.println(usuario);
                        usuarioEncontrado = true;
                    }
                    
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("\n Usuário não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println("Erro em pesquisar Usuário por nome: " + e);
        }
    }
    
    public Usuario pesquisarUsuarioCodigo2(int codigo) { // PRONTO - RETORNA UM USER ENVÉS DE PRINTAR UM USER
        try {
            if(codigo > 0){
                for (Usuario usuario : this.usuarios) {
                    if(usuario.getCodigo() == codigo){
                        return usuario;
                    }
                }
            }
            return null;

        } catch (Exception e) {
            System.out.println("Erro em pesquisar usuário por código2: " + e);
            return null;
        }
    }
    
    //---------------------------------------------------------------------

    // LER LOGS -----------------------------------------------------------
    @SuppressWarnings("unchecked")// COLOQUEI ISSO PQ SE NAO FICA MARCANDO EM LARANJA UM  WARNING NO CAST DE OBJECT IN PARA LIST<LOG>
    public void lerLogsLivro(){ // PRONTO
        //LOGS LIVRO
        System.out.println();
        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logLivro.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            System.out.println("Logs de Livro:");
            for (Log log : logs) {
                if (log instanceof LogLivro) {
                    LogLivro logLivro = (LogLivro) log;
                    System.out.println(logLivro);
                }
            }
            
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs: " + e);
        }
    }
    
    @SuppressWarnings("unchecked") // COLOQUEI ISSO PQ SE NAO FICA MARCANDO EM LARANJA UM  WARNING NO CAST DE OBJECT IN PARA LIST<LOG>
    public void lerLogsUsuario(){ // PRONTO
        //LOGS USER
        System.out.println();
        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logUser.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            System.out.println("Logs de Usuário:");
            for (Log log : logs) {
                if (log instanceof LogUsuario) {
                    LogUsuario logUsuario = (LogUsuario) log;
                    System.out.println(logUsuario);
                    // Acesse os atributos específicos de LogLivro se necessário
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs: " + e);
        }
    }
   
    @SuppressWarnings("unchecked") // COLOQUEI ISSO PQ SE NAO FICA MARCANDO EM LARANJA UM  WARNING NO CAST DE OBJECT IN PARA LIST<LOG>
    public void lerLogsEmprestimo(){ // PRONTO
        //LOGS EMPRESTIMO
        System.out.println();
        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logEmprestimo.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
        
            System.out.println("Logs de Empréstimo:");
            for (Log log : logs) {
                LogEmprestimo logEmprestimo = (LogEmprestimo) log;
                System.out.println(logEmprestimo.getMensagem() + logEmprestimo.getEmprestimo());
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os logs: " + e);
        }
    }
    //----------------------------------------------------------------------


    // RELATÓRIOS -------------------------------------------------------------

    public void getEmprestimosAtrasados(){ // PRONTO
        try {
            /** PARA TESTAR O ATRASO COPIAR dataTesteAtraso no compareTO E DESCOMENTAR O IMPORT DO DateTimeFormatter QUE DEIXEI COMENTADO PRA NAO FICAR COM WARNING */
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            //LocalDateTime dataTestarAtraso = LocalDateTime.parse("20/06/2023 15:03", formatter);
            if(this.emprestimos.size() > 0){
                for (Emprestimo emprestimo : this.emprestimos) { 
                    if(emprestimo.getDataDevolucao().compareTo(LocalDateTime.now()) <= 0){ // A DATA DE DEVOLUÇÃO É MAIOR QUE A DATA ATUAL?
                        System.out.println("\n" + emprestimo);
                    } else {
                        System.out.println("Não há nenhum empréstimo em atraso!!!");
                    }
                }
            } else {
                System.out.println("Não há nenhum empréstimo cadastrado!!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar empréstimos em atraso: " + e);
        }

    }

    public void getListarLivrosEmprestados(){ // PRONTO
        try {
            boolean temAlgumEmprestado = false;
            if(this.livros.size() > 0){
                for (Livro livro : this.livros) {
                    if(livro.getQuantidadeVezesEmprestado() > 0){
                        System.out.println(livro);
                        temAlgumEmprestado = true;
                    }
                }
                if(!temAlgumEmprestado){
                    System.out.println("Nenhum livro foi emprestado!!");
                }
            } else {
                System.out.println("Não há nenhum livro cadastrado !!!");
            }
        } catch (Exception e) {
            System.out.println("Erro em listar livros Emprestados" + e);
        }
    }

    public void getLivrosPopulares(){ // PRONTO
        try {
            boolean temAlgumPopular = false;
            if(this.livros.size() > 0){
                for (Livro livro : this.livros) {
                    if(livro.getQuantidadeVezesEmprestado() > 3){
                        System.out.println(livro);
                        temAlgumPopular = true;
                    }
                }
                if(!temAlgumPopular){
                    System.out.println("Nenhum livro é popular!! (emprestado mais de 3 vezes)");
                }
            } else {
                System.out.println("Não há nenhum livro cadastrado !!!");
            }
        } catch (Exception e) {
            System.out.println("Erro em listar livros mais populares: " + e);
        }
    }

    public void getUsuarioEmprestimoEmAndamento(){ // PRONTO
        if(this.usuarios.size() > 0){
            boolean temAlgum = false;
            try {
                for (Usuario usuario : this.usuarios) {
                    if(usuario.temLivroEmprestado()){
                        System.out.println(usuario);
                        temAlgum = true;
                    }
                }
                if(!temAlgum){
                    System.out.println("Não há nenhum usuário com empréstimo em andamento");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar usuario com empréstimo em andamento" + e);
            }
        } else {
            System.out.println("Não há nenhum Usuário cadastrado!");
        }
    }
    //---------------------------------------------------------------------


    // LISTAR TODOS ------------------------------------------------------------
    
    public void listarTodosLivros(){ // PRONTO
        try {
            if(this.livros.size() > 0){
                for (Livro livro : this.livros) {
                    System.out.println(livro);
                }
            } else {
                System.out.println("Não há nenhum Livro cadastrado (tempo de execução)");
            }
        } catch (Exception e) {
            System.out.println("Erro em listar Livros: " + e);
        }
    }

    public void listarTodosUsuarios(){ // PRONTO
        try {
            if(this.usuarios.size() > 0){
                for(Usuario usuario : this.usuarios){
                    System.out.println(usuario);
                }
            } else {
                System.out.println("Não há nenhum Usuário cadastrado (tempo de execução)");
            }
        } catch (Exception e) {
            System.out.println("Erro em listar usuários: " + e);
        }
    }

    public void listarTodosEmprestimos(){ // PRONTO
        try {
            if(this.emprestimos.size() > 0){
                for(Emprestimo emprestimo : this.emprestimos){
                    System.out.println(emprestimo);
                }
            } else {
                System.out.println("Não há nenhum Empréstimo cadastrado (tempo de execução)");
            }
        } catch (Exception e) {
            System.out.println("Erro em listar empréstimos: " + e);
        }
    }

    //---------------------------------------------------------------------

    public List<Emprestimo> getEmprestimos() { // PRONTO
        return emprestimos;
    }

    public List<Livro> getLivros() { // PRONTO
        return livros;
    }

    public List<Usuario> getUsuarios() { // PRONTO
        return usuarios;
    }

    @Override
    public String toString() {
        return "Biblioteca [livros=" + this.livros + "]";
    }
}
