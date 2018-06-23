package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CVeACarcel extends Casilla implements Serializable{

    public CVeACarcel(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
    }

    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        jugador.setCarcel(true);
        jugador.setPosicion(10);
        servidor.mandarNotificacion(jugador, "Vas a la Carcel", "Haz caido en \"Ir a la Carcel\"");
    }
}
