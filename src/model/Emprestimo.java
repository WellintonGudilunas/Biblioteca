package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import controller.Salvar;

public class Emprestimo implements Serializable{

    private Livro livro;
    private Usuario usuario;
    private LocalDateTime dataEmprestimo, dataDevolucao, dataDevolucaoEfetiva;
    private Salvar salvar;
    private int codigoEmprestimo;
    private List<Livro> livros = new ArrayList<Livro>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

    public Emprestimo() {
        
    }

    public Emprestimo(Livro livro, Usuario usuario, int codigoEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDateTime.now();
        this.dataDevolucao = LocalDateTime.now().plusDays(7);
        this.codigoEmprestimo = codigoEmprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Salvar getSalvar() {
        return salvar;
    }

    public void setSalvar(Salvar salvar) {
        this.salvar = salvar;
    }

    public int getCodigoEmprestimo() {
        return codigoEmprestimo;
    }

    public void setCodigoEmprestimo(int codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    
    public void setDataDevolucaoEfetiva(LocalDateTime dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }

    public LocalDateTime getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Data de empréstimo=" + dataEmprestimo.format(formatter) +  ", Data de devolução Prevista=" + dataDevolucao.format(formatter) +
                ", \nData devolução efetiva: " + (getDataDevolucaoEfetiva() == null ? "Ainda não devolveu" : getDataDevolucaoEfetiva().format(formatter)) +
                ", Código do empréstimo=" + codigoEmprestimo +
                "\nLivro emprestado: " + livro.getTitulo() + ", Código do livro: " + livro.getCodigo() + 
                ", \nUsuario que emprestou: " + usuario.getNome() + ", Codigo do usuario: " + usuario.getCodigo() + 
                "\n";
    }



}
