package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CartaPagarPropiedades extends Carta implements Serializable{
    private int montoCasa;
    private int montoHotel;
    
    public CartaPagarPropiedades(String texto, int montoCasa, int montoHotel){
        this.setTexto(texto);
        this.montoCasa = montoCasa;
        this.montoHotel = montoHotel;
    }

    @Override
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        int montoFinal = jugador.getCasas()*this.montoCasa + jugador.getHoteles()*this.montoHotel;
        if (jugador.getDinero() - montoFinal <= 0){
            jugador.setDinero(0);
            jugador.setPerdio(true);
        }
        else
            jugador.setDinero(jugador.getDinero()-montoFinal);
    }
}
