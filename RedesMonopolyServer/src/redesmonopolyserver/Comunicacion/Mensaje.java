/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;

/**
 *
 * @author alexd
 */
public class Mensaje implements Serializable{
    Tablero tablero;
    int tipo;
    String mensaje;
    String jugador;
    int pos1;
    int pos2;
    int pos3;
    int pos4;

    public Mensaje(int tipo, String mensaje, Tablero tablero) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.tablero=new Tablero();
        this.tablero.actualizar(tablero);
        if(tablero.getJugadores().size()>=1) this.pos1= tablero.getJugadores().get(0).getPosicion();
        if(tablero.getJugadores().size()>=2) this.pos2= tablero.getJugadores().get(1).getPosicion();
        if(tablero.getJugadores().size()>=3) this.pos3= tablero.getJugadores().get(2).getPosicion();
        if(tablero.getJugadores().size()>=4) this.pos4= tablero.getJugadores().get(3).getPosicion();
    }
    
    
}
