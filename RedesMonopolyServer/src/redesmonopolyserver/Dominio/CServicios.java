
package redesmonopolyserver.Dominio;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import redesmonopolyserver.Comunicacion.Servidor;

public class CServicios extends Casilla implements Serializable{
    private int propietario;

    public CServicios(int propietario, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
    }

    public int getPropietario() {
        return propietario;
    }

    @Override
    public void alSalir() {
       }

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        int montoPagar = tablero.getDado1()+tablero.getDado2();
        //Servicio de electricidad
        CServicios electricidad = (CServicios) tablero.getCasillas().get(12); 
        //Servicio de agua
        CServicios agua = (CServicios) tablero.getCasillas().get(28); 
        //Propietario de la casilla actual
        Jugador dueno = tablero.getJugadores().get(this.propietario);
        
        if(electricidad.getPropietario() == agua.getPropietario()){
            if(jugador.getDinero() - 10 * montoPagar < 0){
                dueno.setDinero(jugador.getDinero());
                jugador.setDinero(0);
            }
            else{
                jugador.setDinero(jugador.getDinero() - 10 * montoPagar);
                dueno.setDinero(dueno.getDinero() + 10 * montoPagar);
            }
        }
        else{
            if(jugador.getDinero() - 4 * montoPagar < 0){
                dueno.setDinero(jugador.getDinero());
                jugador.setDinero(0);
            }
            else{
                jugador.setDinero(jugador.getDinero() - 4 * montoPagar);
                dueno.setDinero(dueno.getDinero() + 4 * montoPagar);
            }
        }
        
    }

}
