package redesmonopolyserver.Dominio;

import java.io.Serializable;
/**
 *
 * @author kamgm
 */
public class CartaCobrarJugadores extends Carta implements Serializable{
    private int monto;
    
    public CartaCobrarJugadores(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador) {
        for(int i = 0; i < tablero.getJugadores().size(); i++){
            tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() - this.monto);
        }
        jugador.setDinero(jugador.getDinero() + tablero.getJugadores().size() * this.monto);
    }
    
}
