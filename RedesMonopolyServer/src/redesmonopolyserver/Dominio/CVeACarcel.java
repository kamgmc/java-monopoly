/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Dominio;

/**
 *
 * @author kamgm
 */
public class CVeACarcel extends Casilla {

    public CVeACarcel(String nombre) {
        super(nombre);
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
