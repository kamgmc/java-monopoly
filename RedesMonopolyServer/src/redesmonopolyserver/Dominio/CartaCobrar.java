package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CartaCobrar extends Carta implements Serializable{
    
    private int monto;
    
    public CartaCobrar(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }
    
    @Override
    public void Efecto() {
        //Sumar monto al jugador actual
    }
    
}
