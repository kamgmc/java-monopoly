/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;

/**
 *
 * @author alexd
 */
public class Mensaje implements Serializable{
    int tipo;
    String titulo;
    String mensaje;
    String jugador;
    String tablero;

    public Mensaje(int tipo, String mensaje, Tablero tablero) {
        this.tipo = tipo;
        this.mensaje = mensaje; 
        this.tablero= tablero.getJSon();
    }
    
    public Mensaje(int tipo, String titulo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje; 
        this.titulo = titulo;
    }
    
    
}
