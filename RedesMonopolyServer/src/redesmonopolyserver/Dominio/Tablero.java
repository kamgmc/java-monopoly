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
        turno = false;
        dado1 = 6;
        dado2 = 6;
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
        
    public int obtenerJugador(String nombre){
        int i = 0;
        for(Jugador j: Jugadores){
            if(j.getNombre().equals(nombre)) return i;
            i++;
        }
        return -1;
    }

    public int getDado1() {
        return dado1;
    }

    public void setDado1(int dado1) {
        this.dado1 = dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public void setDado2(int dado2) {
        this.dado2 = dado2;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
    
    public void imprimirTablero(){        
        System.out.println("Estado del tablero:");
        for(Jugador j:Jugadores){
            System.out.println("Juagador: "+j.getNombre()+" en casilla: "+j.getPosicion());
            
        }
    }
    
    public void actualizar(Tablero t){
        Casillas= t.Casillas;
        this.Jugadores = new ArrayList<Jugador>();
        for(Jugador j: t.Jugadores) this.Jugadores.add(j);
        dado1 = t.dado1;
        dado2 =t.dado2;
        turno = t.turno;
    }
    
    public void actualizarUsuarios(int pos1, int pos2, int pos3, int pos4){
        if(Jugadores.size()>=1) Jugadores.get(0).setPosicion(pos1);
        if(Jugadores.size()>=2) Jugadores.get(1).setPosicion(pos2);
        if(Jugadores.size()>=3) Jugadores.get(2).setPosicion(pos3);
        if(Jugadores.size()>=4) Jugadores.get(3).setPosicion(pos4);;
    }
    
    public void asignarUsuarios(){
        int i=0;
        for(Jugador j: Jugadores){
            j.setIcono(i);
            i++;
        }
    }
}
