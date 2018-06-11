package redesmonopolyserver.Dominio;

import java.io.Serializable;

public class CCarta extends Casilla implements Serializable{
    private String tipo;

    public CCarta(String tipo, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.tipo = tipo;
    }

    

    @Override
    public void alSalir() {
     }

    @Override
    public void alLlegar() {
        }
    
}
