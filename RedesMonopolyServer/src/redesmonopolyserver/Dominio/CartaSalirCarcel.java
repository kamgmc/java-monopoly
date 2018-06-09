package redesmonopolyserver.Dominio;
/**
 *
 * @author kamgm
 */
public class CartaSalirCarcel extends Carta{
    
    public CartaSalirCarcel(String texto) {
        this.setTexto(texto);
    }
    @Override
    public void Efecto() {
        //Mantener esta carta hasta que la persona vaya a la carcel.
    }
    
}
