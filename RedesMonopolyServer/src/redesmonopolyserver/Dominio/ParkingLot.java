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
public class ParkingLot extends Casilla implements Serializable{

    public ParkingLot(String nombre) {
        super(nombre);
    }

    @Override
    public void alSalir() {
       }

    @Override
    public void alLlegar() {
     //Recoger dinero
    }
    
}
