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
    public void Efecto() {
        //Descontarle al usuario actual el monto*3 y sumarselo a los demas jugadores
    }
}
