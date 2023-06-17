package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Log implements Serializable {
    private static final long serialVersionUID = 123456789L;
    protected String mensagem;
    protected LocalDateTime dataHora;

    public Log(String mensagem, LocalDateTime dataHora) {
        this.dataHora = LocalDateTime.now();
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public String toString() {
        return "Log [mensagem=" + mensagem + "]";
    }

    

}