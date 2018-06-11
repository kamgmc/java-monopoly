/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.Serializable;

/**
 *
 * @author alexd
 */
public class Solicitud implements Serializable{
    String jugador;
    int tipo;

    public Solicitud(String jugador, int tipo) {
        this.jugador = jugador;
        this.tipo = tipo;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
