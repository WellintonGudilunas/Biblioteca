package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Emprestimo;
import model.Livro;
import model.Log;
import model.LogEmprestimo;
import model.LogLivro;
import model.LogUsuario;
import model.Usuario;

public class Salvar implements Serializable {
    public List<LogUsuario> logUsuarios;
    private List<LogLivro> logLivros;
    private List<LogEmprestimo> logEmprestimos;

    public Salvar() {
        logUsuarios = new ArrayList<>();
        logLivros = new ArrayList<>();
        logEmprestimos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public void exportarDados() {
        // Exportar log de usuários
        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logUser.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
            for (Log log : logs) {
                if (log instanceof LogUsuario) {
                    LogUsuario logUsuario = (LogUsuario) log;
                    this.logUsuarios.add(logUsuario);
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os de Usuário ao EXPORTAR DADOS: " + e);
        }   

        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logLivro.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
            for (Log log : logs) {
                if (log instanceof LogLivro) {
                    LogLivro logLivro = (LogLivro) log;
                    this.logLivros.add(logLivro);
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os de Livro ao EXPORTAR DADOS: " + e);
        }   

        try {
            FileInputStream fileIn = new FileInputStream("src/model/logs/logEmprestimo.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        
            List<Log> logs = (List<Log>) objectIn.readObject();
            for (Log log : logs) {
                if (log instanceof LogEmprestimo) {
                    LogEmprestimo logEmprestimo = (LogEmprestimo) log;
                    this.logEmprestimos.add(logEmprestimo);
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler os de Empréstimo ao EXPORTAR DADOS: " + e);
        }   
    }

    public void adicionarLogUsuario(String mensagem, LocalDateTime data, Usuario usuario) {
        try {
            LogUsuario log = new LogUsuario(mensagem, data, usuario);
            logUsuarios.add(log);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar log de usuario na lista LogUsuario na classe Salvar" + e);
        }

    }

    public void adicionarLogLivro(String mensagem, LocalDateTime data, Livro livro ) {
        try {
            LogLivro log = new LogLivro(mensagem, data, livro);
            logLivros.add(log);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar log de livro na lista LogLivro na classe Salvar" + e);
        }

    }

    public void adicionarLogEmprestimo(String mensagem, LocalDateTime data, Emprestimo emprestimo ) {
        try {
            LogEmprestimo log = new LogEmprestimo(mensagem, data, emprestimo);
            logEmprestimos.add(log);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar log de Emprestimo na lista de LogEmprestimo na classe Salvar" + e);
        }
    }
    
    public void salvarLogUsuario(String nomeArquivo) {
        try {
            boolean exists = (new File("src/model/logs/a")).exists();
            if(!exists){
                File SalvarUsuario = new File("src/model/logs/a");
                SalvarUsuario.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            List<Log> logs = new ArrayList<>(logUsuarios);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de usuários salvos com sucesso em " + nomeArquivo);

            objectOut.close();
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de usuário: " + e);
        }
    }

    public void salvarLogLivro(String nomeArquivo) {
        try {
            boolean exists = (new File("src/model/logs/a")).exists();
            if(!exists){
                File SalvarUsuario = new File("src/model/logs/a");
                SalvarUsuario.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            List<Log> logs = new ArrayList<>(logLivros);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de livro salvos com sucesso em " + nomeArquivo);

            objectOut.close();
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de livro: " + e);
        }
    }

    public void salvarLogEmprestimo(String nomeArquivo) {
        
        try {
            boolean exists = (new File("src/model/logs/a")).exists();
            if(!exists){
                File SalvarUsuario = new File("src/model/logs/a");
                SalvarUsuario.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
             
            List<Log> logs = new ArrayList<>(logEmprestimos);
    
            objectOut.writeObject(logs);
            System.out.println("Logs de Empréstimos salvos com sucesso em " + nomeArquivo);

            objectOut.close();
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Erro ao salvar os logs de Empréstimos: " + e);
        }
    }
}