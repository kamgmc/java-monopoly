/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Dominio;

import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

/**
 *
 * @author kamgm
 */
public class CFerrocarril extends Casilla implements Serializable{
    private int propietario;

    public CFerrocarril(int propietario, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
    }

    

    @Override
    public void alSalir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        //Servicio de electricidad
        CFerrocarril Reading = (CFerrocarril) tablero.getCasillas().get(5);
        CFerrocarril Tren2 = (CFerrocarril) tablero.getCasillas().get(15);
        CFerrocarril Tren3 = (CFerrocarril) tablero.getCasillas().get(25);
        CFerrocarril Tren4 = (CFerrocarril) tablero.getCasillas().get(35);
        //Propietario de la casilla actual
        /*
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
*/
    }
    
}
