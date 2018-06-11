package redesmonopolyserver.Dominio;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author kamgm
 */
public abstract class Casilla implements Serializable{
    
    private String nombre;
    private int posJugadorX;
    private int posJUgadorY;

    public Casilla(String nombre) {
        this.nombre = nombre;
        
    }

    public Casilla(String nombre, int posJugadorX, int posJUgadorY) {
        this.nombre = nombre;
        this.posJugadorX = posJugadorX;
        this.posJUgadorY = posJUgadorY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosJugadorX() {
        return posJugadorX;
    }

    public void setPosJugadorX(int posJugadorX) {
        this.posJugadorX = posJugadorX;
    }

    public int getPosJUgadorY() {
        return posJUgadorY;
    }

    public void setPosJUgadorY(int posJUgadorY) {
        this.posJUgadorY = posJUgadorY;
    }


    
    
    public abstract void alSalir();
    public abstract void alLlegar();
}
