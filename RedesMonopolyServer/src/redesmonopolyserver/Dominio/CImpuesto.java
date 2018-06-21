package redesmonopolyserver.Dominio;

import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CImpuesto extends Casilla implements Serializable{
    private int monto;

    public CImpuesto(int monto, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.monto = monto;
    }

    @Override
    public void alSalir() {
        }

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
       jugador.setDinero(jugador.getDinero() - this.monto);
    }


}
