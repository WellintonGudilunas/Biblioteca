package model;

import java.io.Serializable;

public class Livro implements Serializable {
    
    private String titulo, autor, categoria;
    private int quantidadeDisponivel, codigo, anoPublicacao;
    private int quantidadeVezesEmprestado;

    public Livro() {
    }

    public Livro(String titulo, String autor, int anoPublicacao, String categoria, int quantidadeDisponivel, int codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
        public int getQuantidadeVezesEmprestado() {
        return quantidadeVezesEmprestado;
    }

    public void setQuantidadeVezesEmprestado(int quantidadeVezesEmprestado) {
        this.quantidadeVezesEmprestado = quantidadeVezesEmprestado;
    }

    @Override
    public String toString() {
        return "Livro [codigo=" + codigo + ", Quantidade vezes emprestado: " + quantidadeVezesEmprestado + ", titulo=" + titulo + ", autor=" + autor + ", anoPublicacao=" + anoPublicacao + ", categoria="
                + categoria + ", quantidadeDisponivel=" + quantidadeDisponivel + "]";
    }

    

    
   
    
}
