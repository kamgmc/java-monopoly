
package redesmonopolyserver.Dominio;

import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CServicios extends Casilla implements Serializable{
    private int propietario;

    public CServicios(int propietario, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
    }

    @Override
    public void alSalir() {
       }

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        
    }

}
