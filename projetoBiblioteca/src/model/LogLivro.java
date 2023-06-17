package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogLivro extends Log {
    private static final long serialVersionUID = 123456789L;
    private Livro livro;

    public LogLivro(String mensagem, LocalDateTime dataHora, Livro livro) {
        super(mensagem, dataHora);
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraFormatada = dataHora.format(formatter);

        return "\nLogLivro: " + this.mensagem + dataHoraFormatada +
                "\nlivro=" + livro;
    }
    

}
