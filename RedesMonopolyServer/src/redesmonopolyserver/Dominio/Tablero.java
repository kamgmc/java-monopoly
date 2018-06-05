package redesmonopolyserver.Dominio;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Casilla> Casillas;
    private ArrayList<Jugador> Jugadores;
    private int montoImpuestos;

    public Tablero() {
        this.Casillas = new ArrayList<Casilla>();
        this.Jugadores = new ArrayList<Jugador>();
        this.montoImpuestos = 0;
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

    public int getMontoImpuestos() {
        return montoImpuestos;
    }

    public void setMontoImpuestos(int montoImpuestos) {
        this.montoImpuestos = montoImpuestos;
    }
    
    
}
