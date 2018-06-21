package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CartaPagarJugadores extends Carta implements Serializable{
    private int monto;
    
    public CartaPagarJugadores(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador) {
        for(int i = 0; i < tablero.getJugadores().size(); i++){
            tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() + this.monto);
        }
        jugador.setDinero(jugador.getDinero() - tablero.getJugadores().size() * this.monto);
    }
    
}
