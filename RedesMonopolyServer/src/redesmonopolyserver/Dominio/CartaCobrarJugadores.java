package redesmonopolyserver.Dominio;
/**
 *
 * @author kamgm
 */
public class CartaCobrarJugadores extends Carta{
    private int monto;
    
    public CartaCobrarJugadores(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }
    
    @Override
    public void Efecto() {
        //Sumarle al usuario actual el monto*3 y restarselo a los demas jugadores
    }
}
