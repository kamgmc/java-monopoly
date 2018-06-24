package redesmonopolyserver.Dominio;
import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Comunicacion.Servidor;

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
                tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
            }
            else{
                if(casillas.get(0) - jugador.getPosicion() < 0 && !jugador.isCarcel()){
                    jugador.setDinero(jugador.getDinero() + 200);
                }
                //Se establece la posicion asignada por la carta
                jugador.setPosicion(casillas.get(0));
                if(casillas.get(0)==10) jugador.setCarcel(true);
                tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
            }
        }
        else if(casillas.size() == 2){
            int lastPosition = jugador.getPosicion();
            boolean salir = false;
            //Decide cual de los dos Servicios esta mas cerca
            while (!salir){
                if (lastPosition == casillas.get(0)){
                        jugador.setPosicion(casillas.get(0));
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                if (lastPosition == casillas.get(1)){
                        jugador.setPosicion(casillas.get(1));
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                lastPosition++;
                if (lastPosition == 40){ 
                    lastPosition = 0;
                    jugador.setDinero(jugador.getDinero() + 200);
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
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                if (lastPosition == casillas.get(1)){
                        jugador.setPosicion(casillas.get(1));
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                if (lastPosition == casillas.get(2)){
                        jugador.setPosicion(casillas.get(2));
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                if (lastPosition == casillas.get(3)){
                        jugador.setPosicion(casillas.get(3));
                        tablero.getCasillas().get(jugador.getPosicion()).alLlegar(tablero, jugador, servidor);
                        salir = true;}
                lastPosition++;
                if (lastPosition == 40){ 
                    lastPosition = 0;
                    jugador.setDinero(jugador.getDinero() + 200);
                } 
            }
        }
    }
}
