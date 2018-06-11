package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CartaAvanzar extends Carta implements Serializable{

    public CartaAvanzar(String texto/*, Casilla casilla*/) {
        this.setTexto(texto);
    }
    
    @Override
    public void Efecto() {
        
    }
    
}
