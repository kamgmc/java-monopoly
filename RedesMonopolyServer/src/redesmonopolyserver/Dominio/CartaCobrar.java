package redesmonopolyserver.Dominio;
/**
 *
 * @author kamgm
 */
public class CartaCobrar extends Carta{
    
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
