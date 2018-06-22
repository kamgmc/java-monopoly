package redesmonopolyserver.Dominio;

import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

/**
 *
 * @author kamgm
 */
public class CartaSalirCarcel extends Carta implements Serializable{
    
    public CartaSalirCarcel(String texto) {
        this.setTexto(texto);
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        jugador.setCarcelLibre(jugador.getCarcelLibre() + 1);
    }
    
}
