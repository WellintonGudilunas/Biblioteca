package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUsuario extends Log {
    private Usuario usuario;

    public LogUsuario(String mensagem, LocalDateTime dataHora, Usuario usuario){
        super(mensagem, dataHora);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraFormatada = dataHora.format(formatter);
        return "\nLogUsuario: " + this.mensagem + dataHoraFormatada +
        "\nUsuario=" + usuario;
    }

}