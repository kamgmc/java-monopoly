package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CartaPagarPropiedades extends Carta implements Serializable{
    private int montoCasa;
    private int montoHotel;
    
    public CartaPagarPropiedades(String texto, int montoCasa, int montoHotel){
        this.setTexto(texto);
        this.montoCasa = montoCasa;
        this.montoHotel = montoHotel;
    }
    
    @Override
    public void Efecto() {
        //Contar Numero de casas y hoteles, pagar montos establecidos
    }
    
}
