package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CartaCobrarJugadores extends Carta implements Serializable{
    private int monto;
    
    public CartaCobrarJugadores(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        for(int i = 0; i < tablero.getJugadores().size(); i++){
            tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() - this.monto);
            servidor.mandarNotificacion(tablero.getJugadores().get(i), "Pagas dinero por Carta", "Pagas " + this.monto + "$ por carta de " + jugador.getNombre());
        }
        jugador.setDinero(jugador.getDinero() + tablero.getJugadores().size() * this.monto);
    }   
}
