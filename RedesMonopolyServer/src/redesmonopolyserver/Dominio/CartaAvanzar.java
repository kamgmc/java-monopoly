package redesmonopolyserver.Dominio;
import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Comunicacion.Servidor;
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
    public void Efecto(Tablero tablero, Jugador jugador, Servidor servidor) {
        if(casillas.size() == 1){
            //Si el numero de casillas es negativa, hay que retroceder
            if(casillas.get(0) < 0){
                jugador.setPosicion(jugador.getPosicion() + casillas.get(0));
            }
            else{
                if(casillas.get(0) - jugador.getPosicion() < 0 && !jugador.isCarcel()){
                    jugador.setDinero(jugador.getDinero() + 200);
                }
                //Se establece la posicion asignada por la carta
                jugador.setPosicion(casillas.get(0));
                if(casillas.get(0)==10) jugador.setCarcel(true);
            }
        }
        else if(casillas.size() == 2){
            int lastPosition = jugador.getPosicion();
            boolean salir = false;
            //Decide cual de los dos Servicios esta mas cerca
            while (!salir){
                if (lastPosition == casillas.get(0)){
                        jugador.setPosicion(casillas.get(0));
                        salir = true;}
                if (lastPosition == casillas.get(1)){
                        jugador.setPosicion(casillas.get(1));
                        salir = true;}
                lastPosition++;
                if (lastPosition==40){ 
                    lastPosition = 0;
                    jugador.setDinero(jugador.getDinero()+200);
                } 
            }
            
        }
        else if(casillas.size() == 4){
            int lastPosition = jugador.getPosicion();
            boolean salir = false;
            //Decide cual de los dos Servicios esta mas cerca
            while (!salir){
                if (lastPosition == casillas.get(0)){
                        jugador.setPosicion(casillas.get(0));
                        salir = true;}
                if (lastPosition == casillas.get(1)){
                        jugador.setPosicion(casillas.get(1));
                        salir = true;}
                if (lastPosition == casillas.get(2)){
                        jugador.setPosicion(casillas.get(2));
                        salir = true;}
                if (lastPosition == casillas.get(3)){
                        jugador.setPosicion(casillas.get(3));
                        salir = true;}
                lastPosition++;
                if (lastPosition==40){ 
                    lastPosition = 0;
                    jugador.setDinero(jugador.getDinero()+200);
                } 
            }
            
        }
    }

    
}
