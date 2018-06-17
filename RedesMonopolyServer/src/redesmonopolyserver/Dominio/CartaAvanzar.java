package redesmonopolyserver.Dominio;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author kamgm
 */
public class CartaAvanzar extends Carta implements Serializable{
    
    private ArrayList<Integer> casillas;

    public CartaAvanzar(String texto, int casilla) {
        casillas = new ArrayList<Integer>();
        this.setTexto(texto);
        casillas.add(casilla);
    }
    public CartaAvanzar(String texto, int servicio1, int servicio2) {
        this.setTexto(texto);
        casillas = new ArrayList<Integer>();
        casillas.add(servicio1);
        casillas.add(servicio2);
    }
    public CartaAvanzar(String texto, int ferrocarril1, int ferrocarril2, int ferrocarril3, int ferrocarril4) {
        this.setTexto(texto);
        casillas = new ArrayList<Integer>();
        casillas.add(ferrocarril1);
        casillas.add(ferrocarril2);
        casillas.add(ferrocarril3);
        casillas.add(ferrocarril4);
    }
    @Override
    public void Efecto(Tablero tablero, Jugador jugador) {
        if(casillas.size() == 1){
            //Si el numero de casillas es negativa, hay que retroceder
            if(casillas.get(0) < 0){
                jugador.setPosicion(jugador.getPosicion() + casillas.get(0));
            }
            else{
                if(casillas.get(0) - jugador.getPosicion() < 0){
                    jugador.setDinero(jugador.getDinero() + 200);
                }
                //Se establece la posicion asignada por la carta
                jugador.setPosicion(casillas.get(0));
            }
        }
        else if(casillas.size() == 2){
            int lastPosition = jugador.getPosicion();
            //Decide cual de los dos Servicios esta mas cerca
            if(casillas.get(0) - jugador.getPosicion() < 0 && casillas.get(1) - jugador.getPosicion() < 0){
                jugador.setPosicion(casillas.get(0));
            }
            else if(casillas.get(1) - jugador.getPosicion() > 0){
                jugador.setPosicion(casillas.get(1));
            }
            else{
                jugador.setPosicion(casillas.get(0));
            }
            //Verifica si pasa por Go
            if(jugador.getPosicion() - lastPosition < 0){
                jugador.setDinero(jugador.getDinero() + 200);
            }
        }
    }

    
}
