package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CartaPagar extends Carta implements Serializable{
    
    private int monto;
    
    public CartaPagar(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador) {
        if (jugador.getDinero() - this.monto < 0){
            jugador.setDinero(0);
        }else 
        jugador.setDinero(jugador.getDinero() - this.monto);
    }
    
}
