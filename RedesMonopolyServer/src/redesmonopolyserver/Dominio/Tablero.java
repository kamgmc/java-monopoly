package redesmonopolyserver.Dominio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import redesmonopolyserver.Persistencia.Generador;

public class Tablero implements Serializable{
    private ArrayList<Casilla> Casillas;
    private ArrayList<Jugador> Jugadores;
    private ArrayList<Carta> fortuna;
    private ArrayList<Carta> arca;
    private int dado1;
    private int dado2;
    private boolean turno;

    public Tablero() {
        this.Casillas = new ArrayList<Casilla>();
        this.Jugadores = new ArrayList<Jugador>();
        fortuna = new ArrayList<Carta>();
        arca = new ArrayList<Carta>();
        Generador.generarTarjetas(fortuna, arca);
        turno = false;
        dado1 = 6;
        dado2 = 6;
    }
    
    public ArrayList<Carta> getFortuna() {
        return fortuna;
    }

    public void setFortuna(ArrayList<Carta> fortuna) {
        this.fortuna = fortuna;
    }

    public ArrayList<Carta> getArca() {
        return arca;
    }

    public void setArca(ArrayList<Carta> arca) {
        this.arca = arca;
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
            System.out.println("\tJuagador: "+j.getNombre()+" en casilla: "+j.getPosicion());
            
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
    
    public String getJSon(){
        JsonObject j = new JsonObject();
        j.addProperty("dado1", dado1);
        j.addProperty("dado2", dado2);
        j.addProperty("turno", turno);
        JsonArray jugadores = new JsonArray();
        for(Jugador jugadorClase: this.Jugadores){
            JsonObject jugadorJson = new JsonObject();
            jugadorJson.addProperty("dinero", jugadorClase.getDinero());
            jugadorJson.addProperty("nombre", jugadorClase.getNombre());
            jugadorJson.addProperty("carcelLibre", jugadorClase.getCarcelLibre());
            jugadorJson.addProperty("ip", jugadorClase.getIp());
            jugadorJson.addProperty("posicion", jugadorClase.getPosicion());
            jugadorJson.addProperty("carcel", jugadorClase.isCarcel());
            jugadorJson.addProperty("casas", jugadorClase.getCasas());
            jugadorJson.addProperty("hoteles", jugadorClase.getHoteles());
            jugadores.add(jugadorJson);
        }
        j.add("jugadores", jugadores);
        return j.toString();
    }
    
    public void setJson(String jsonString){
        JsonParser jParser = new JsonParser();
        JsonObject j = (JsonObject) jParser.parse(jsonString);
        this.dado1= j.get("dado1").getAsInt();
        this.dado2= j.get("dado2").getAsInt();
        this.turno= j.get("turno").getAsBoolean();
        JsonArray jugadores = j.getAsJsonArray("jugadores");
        int numJugadores =jugadores.size();
        this.Jugadores = new ArrayList<Jugador>();
        for (int i = 0;i<numJugadores;i++){
            Jugador jClase = new Jugador();
            JsonObject jObject = jugadores.get(i).getAsJsonObject();
            jClase.setDinero(jObject.get("dinero").getAsInt());
            jClase.setNombre(jObject.get("nombre").getAsString());
            jClase.setCarcelLibre(jObject.get("carcelLibre").getAsInt());
            jClase.setIp(jObject.get("ip").getAsString());
            jClase.setPosicion(jObject.get("posicion").getAsInt());    
            jClase.setCarcel(jObject.get("carcel").getAsBoolean());
            jClase.setCasas(jObject.get("casas").getAsInt());
            jClase.setHoteles(jObject.get("hoteles").getAsInt());
            this.Jugadores.add(jClase);
            
        }
    }
    
    public void acomodarJugadores(){
        Collections.sort(Jugadores, new Comparator<Jugador>(){
            public int compare(Jugador s1, Jugador s2) {
                return s1.getCodigo()-s2.getCodigo();
            }
        });  
    }
    
}
