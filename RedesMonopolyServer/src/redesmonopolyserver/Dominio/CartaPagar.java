package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CartaPagar extends Carta implements Serializable{
    
    private int monto;
    
    public CartaPagar(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        if (jugador.getDinero() - this.monto <= 0){
            jugador.setDinero(0);
            jugador.setPerdio(true);
        }else 
        jugador.setDinero(jugador.getDinero() - this.monto);
    }  
}
