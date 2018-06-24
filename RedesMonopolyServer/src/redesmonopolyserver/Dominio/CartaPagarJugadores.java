package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CartaPagarJugadores extends Carta implements Serializable{
    
    private int monto;
    
    public CartaPagarJugadores(String texto, int monto) {
        this.setTexto(texto);
        this.monto = monto;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        if (jugador.getDinero() - tablero.getJugadores().size() * this.monto <= 0){
            for(int i = 0; i < tablero.getJugadores().size(); i++){
                if(jugador.getDinero() - this.monto > 0){
                    tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() + this.monto);
                    servidor.mandarNotificacion(tablero.getJugadores().get(i), "Cobras dinero por Carta", "Cobras " + this.monto + "$ por carta de " + jugador.getNombre());
                    jugador.setDinero(jugador.getDinero() - this.monto);
                }
                else if(jugador.getDinero() > 0){
                    tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() + jugador.getDinero());
                    servidor.mandarNotificacion(tablero.getJugadores().get(i), "Cobras dinero por Carta", "Cobras " + jugador.getDinero() + "$ por carta de " + jugador.getNombre());
                    jugador.setDinero(0);
                    jugador.setPerdio(true);
                }
            }
        }
        else{
            for(int i = 0; i < tablero.getJugadores().size(); i++){
                tablero.getJugadores().get(i).setDinero(tablero.getJugadores().get(i).getDinero() + this.monto);
                servidor.mandarNotificacion(tablero.getJugadores().get(i), "Cobras dinero por Carta", "Cobras " + this.monto + "$ por carta de " + jugador.getNombre());
            }
            jugador.setDinero(jugador.getDinero() - tablero.getJugadores().size() * this.monto);
        }
    }
    
}
