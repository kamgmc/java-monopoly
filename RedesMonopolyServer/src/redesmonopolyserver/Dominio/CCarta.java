package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CCarta extends Casilla implements Serializable{
    
    private String tipo;

    public CCarta(String tipo, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.tipo = tipo;
    }
    
    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) { 
        if(this.tipo.equals("Fortuna")){
            Carta seleccionada = tablero.getFortuna().get(0);
            seleccionada.Efecto(tablero, jugador, servidor);
            tablero.getFortuna().remove(0);
            tablero.getFortuna().add(seleccionada);
            servidor.mandarNotificacion(jugador, "Fortuna", seleccionada.getTexto());
            System.out.println("Salio la carta: "+seleccionada.getTexto());
        }
        else{
            Carta seleccionada = tablero.getArca().get(0);
            seleccionada.Efecto(tablero, jugador, servidor);
            tablero.getArca().remove(0);
            tablero.getArca().add(seleccionada);
            servidor.mandarNotificacion(jugador, "Arca Comunal", seleccionada.getTexto());
            System.out.println("Salio la carta: "+seleccionada.getTexto());
        }
    }
}