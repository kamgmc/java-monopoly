package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;
public class CartaCobrar extends Carta implements Serializable{
    
    private int monto;
    
    public CartaCobrar(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        jugador.setDinero(jugador.getDinero() + this.monto);
    }
}
