/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.Serializable;
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

    public Mensaje(int tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    
    
}
