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
<<<<<<< HEAD
       if (jugador.getDinero() - this.monto < 0){
            jugador.setDinero(0);
        } else{
        jugador.setDinero(jugador.getDinero() - this.monto);
        }
=======
       jugador.setDinero(jugador.getDinero() - this.monto);
       servidor.mandarNotificacion(jugador, "Cobro de impuestos", "Por impuestos pagas: "+this.monto);
>>>>>>> 21492e3a28008a93b7daa8ef60d5f6b0ca9e5201
    }


}
