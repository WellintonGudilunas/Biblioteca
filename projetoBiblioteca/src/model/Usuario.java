package model;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nome, sobrenome, endereco, email, cpf, telefone;
    private int codigo;

    private boolean livroEmprestado = false;

    public Usuario() {
    }
    public Usuario(int codigo, String nome, String sobrenome, String endereco, String email, String cpf, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean temLivroEmprestado() {
        return livroEmprestado;
    }
    public void setLivroEmprestado(boolean livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public int getCodigo() {
        return codigo;
    }



    @Override
    public String toString() {
        return "Usuario [codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", endereco=" + endereco + ", email=" + email
                + ", cpf=" + cpf + ", telefone=" + telefone + ", Emprestimo?" + livroEmprestado + "]";
    }

    

  
}
