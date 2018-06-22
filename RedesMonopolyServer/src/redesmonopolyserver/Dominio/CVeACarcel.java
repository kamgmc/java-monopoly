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
public class CVeACarcel extends Casilla implements Serializable{

    public CVeACarcel(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
    }

    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        jugador.setCarcel(true);
        jugador.setPosicion(10);
        servidor.mandarNotificacion(jugador, "Haz caido en \"Ir a la Carcel\"", "Vas a la Carcel");
    }

    
}
