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
    public void Efecto() {
        //Descontarle al usuario actual el monto
    }
    
}
