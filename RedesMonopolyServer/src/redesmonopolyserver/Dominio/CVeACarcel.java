/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Dominio;

import java.io.Serializable;

/**
 *
 * @author kamgm
 */
public class CVeACarcel extends Casilla implements Serializable{

    public CVeACarcel(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
    }

    @Override
    public void alSalir() {
    //VACIO    
    }

    @Override
    public void alLlegar() {
    //Mover jugador a CCarcel    
    }
    
}
