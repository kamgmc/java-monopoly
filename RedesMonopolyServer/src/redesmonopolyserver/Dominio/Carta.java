package redesmonopolyserver.Dominio;

import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public abstract class Carta implements Serializable{
    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public abstract void Efecto(Tablero tablero, Jugador jugador, Servidor servidor);
    
}
