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
public class CCarcel extends Casilla implements Serializable{

    public CCarcel(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
    }
    
    

    @Override
    public void alSalir() {
        }

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
       
    }


    
}
