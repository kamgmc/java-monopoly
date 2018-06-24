package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CGo extends Casilla implements Serializable{

    public CGo(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
    }

    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {}    
}
