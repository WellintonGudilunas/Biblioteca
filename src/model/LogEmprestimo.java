package model;

import java.time.LocalDateTime;

public class LogEmprestimo extends Log {
    private Livro livro;
    private Usuario usuario;
    private Emprestimo emprestimo;

    public LogEmprestimo(String mensagem, LocalDateTime dataHora, Emprestimo emprestimo) {
        super(mensagem, dataHora);
        this.emprestimo = emprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    @Override
    public String toString() {
        return "LogEmprestimo [livro=" + livro + ", usuario=" + usuario + ", emprestimo=" + emprestimo + "]";
    }
}