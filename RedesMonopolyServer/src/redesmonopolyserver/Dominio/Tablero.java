package redesmonopolyserver.Dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Tablero implements Serializable{
    private ArrayList<Casilla> Casillas;
    private ArrayList<Jugador> Jugadores;
    private int dado1;
    private int dado2;
    private boolean turno;

    public Tablero() {
        this.Casillas = new ArrayList<Casilla>();
        this.Jugadores = new ArrayList<Jugador>();
    }
    
    public ArrayList<Casilla> getCasillas() {
        return Casillas;
    }

    public void setCasillas(ArrayList<Casilla> Casillas) {
        this.Casillas = Casillas;
    }

    public ArrayList<Jugador> getJugadores() {
        return Jugadores;
    }

    public void setJugadores(ArrayList<Jugador> Jugadores) {
        this.Jugadores = Jugadores;
    }
    
    public int obtenerPosicion(Casilla casilla){
        int i = 0;
        for(Casilla c: Casillas){
            if(c.equals(casilla)) return i;
            i++;
        }
        return -1;
    }
    
}
