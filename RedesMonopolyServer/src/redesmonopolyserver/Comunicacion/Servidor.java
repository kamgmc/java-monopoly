/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import redesmonopolyserver.Dominio.Carta;
import redesmonopolyserver.Dominio.Casilla;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Persistencia.Generador;
public class Servidor {
        ServerSocket ss;
        ArrayList<ConexionUsuario> conexiones;
        Tablero tablero;
        ArrayList<Carta> fortuna;
        ArrayList<Carta> arca;
        Socket socket;
        
    public Servidor() throws IOException {
        conexiones = new ArrayList<ConexionUsuario>();
        System.out.print("Inicializando servidor... ");
        tablero = new Tablero();
        Generador.GenerarCasillas(tablero);
        Generador.generarTarjetas(fortuna, arca);
        ss = new ServerSocket(10578);
        System.out.println("\tConexion realizada");
        while (conexiones.size()<4) {
            socket = ss.accept();
            ConexionUsuario c = new ConexionUsuario(socket);
            System.out.println("Se ha conectado un usuario: "+socket);
            new Thread(new Listener(this,c)).start();
            conexiones.add(c);
        }
        System.out.println("Se crearon todos los usuarios");

            try {
                
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        long seed = System.nanoTime();
        Collections.shuffle(conexiones,new Random(seed));
        Collections.shuffle(tablero.getJugadores(),new Random(seed));
        mandarTablero(0);

    }
    
    public void mandarTablero(int jugadorTurno){
        int cont=0;
        for(ConexionUsuario c:conexiones){
            if(cont==jugadorTurno){
                try {
                    tablero.setTurno(true);
                    System.out.println("Es el turno de: "+tablero.getJugadores().get(cont).getNombre());
                    c.getDos().flush();
                    c.getDos().writeObject(new Mensaje(0,"Tablero Actualizado",tablero));
                    tablero.setTurno(false);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hubo un error");
                }
            }
            else{
                try {
                    tablero.setTurno(false);
                    System.out.println("No es el turno de: "+tablero.getJugadores().get(cont).getNombre());
                    c.getDos().flush();
                    c.getDos().writeObject(new Mensaje(0,"Tablero Actualizado",tablero));
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hubo un error");
                }
            }
            cont++;
        }
        
    }
        
    public void procesarSolicitud(Solicitud s){
        if(s.tipo==0){
            Jugador j = new Jugador(s.getJugador(),"localhost",0);
            tablero.getJugadores().add(j);
            System.out.println("Se creo el usuario: "+j.getNombre());
            }
        else if(s.tipo==1){
            int jugador = tablero.obtenerJugador(s.jugador);
            Jugador j = tablero.getJugadores().get(jugador);
            tablero.setDado1((int)(1+Math.random()*6));
            tablero.setDado2((int)(1+Math.random()*6));
            System.out.println("Dados: "+tablero.getDado1()+" "+tablero.getDado2());
            for(int i=0;i<tablero.getDado1()+tablero.getDado2();i++){
                mover(j);
                mandarTablero(-1);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tablero.imprimirTablero();
            mandarTablero(siguienteJugador(jugador));
        }
        else if(s.tipo==2){
            mandarTablero(siguienteJugador(-1));
        }
    }
    
    public int siguienteJugador(int jugadorAnterior){
        int siguienteJugador = jugadorAnterior+1;
        while(siguienteJugador>=tablero.getJugadores().size()){
            siguienteJugador= siguienteJugador-tablero.getJugadores().size();
        }
        return siguienteJugador;
    }
    
    public void mover(Jugador j){
        int nuevaPosicion=j.getPosicion()+1;
        while(nuevaPosicion>=tablero.getCasillas().size()){
            nuevaPosicion= nuevaPosicion-tablero.getCasillas().size();
            j.setDinero(j.getDinero()+200);
        }
        j.setPosicion(nuevaPosicion);
        
    }
    
    
    public class Listener implements Runnable{
        private Servidor s;
        ConexionUsuario c;

        public Listener(Servidor s, ConexionUsuario c) {
            this.s = s;
            this.c = c;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("Esperando Solicitud");
                    Solicitud sol = (Solicitud) c.getDis().readObject();
                    s.procesarSolicitud(sol);
                    System.out.println("Solicitud Recibida");
                } catch (IOException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

