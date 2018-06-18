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
        Socket socket;
    private int contadorTurnos;
        
    public Servidor() throws IOException {
        contadorTurnos =0;
        conexiones = new ArrayList<ConexionUsuario>();
        System.out.print("Inicializando servidor... ");
        tablero = new Tablero();
        Generador.GenerarCasillas(tablero);
        ss = new ServerSocket(10578);
        System.out.println("\tConexion realizada");
        while (conexiones.size()<2) {
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
        tablero.asignarUsuarios();
        mandarTablero(0);

    }
    
    public void mandarNotificacion(Jugador j,String titulo, String mensaje){
        int pos = tablero.obtenerJugador(j.getNombre());
        ConexionUsuario c = this.conexiones.get(pos);
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(1,titulo,mensaje));
            System.out.print("Se ha enviado la notificacion: "+titulo);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            // El jugador solicito unirse a la partida
            Jugador j = new Jugador(s.getJugador(),"localhost",0);
            tablero.getJugadores().add(j);
            System.out.println("Se creo el usuario: "+j.getNombre());
            }
        else if(s.tipo==1){
        // El jugador solicito moverse
            int jugador = tablero.obtenerJugador(s.jugador);
            Jugador j = tablero.getJugadores().get(jugador);
            System.out.println("Dados: "+tablero.getDado1()+" "+tablero.getDado2());
            int posFinal=0;
            contadorTurnos +=1;
            tablero.setDado1((int)(1+Math.random()*6));
            tablero.setDado2((int)(1+Math.random()*6));
            tablero.setDado1(1);
            tablero.setDado2(0);
            for(int i=0;i<tablero.getDado1()+tablero.getDado2();i++){
                posFinal = mover(j);
                mandarTablero(-1);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tablero.getCasillas().get(posFinal).alLlegar(tablero, j, this);
            if (tablero.getDado1() != tablero.getDado2()) {
                mandarTablero(siguienteJugador(jugador));
                contadorTurnos =0;
            }
            else 
                {
                    if (contadorTurnos == 3){
                        j.setPosicion(10);
                        j.setCarcel(true);
                        contadorTurnos =0;
                        mandarTablero(siguienteJugador(jugador));
                    }
                    else mandarTablero(jugador);
                }


                tablero.imprimirTablero();

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
    
    public int mover(Jugador j){
        int nuevaPosicion=j.getPosicion()+1;
        while(nuevaPosicion>=tablero.getCasillas().size()){
            nuevaPosicion= nuevaPosicion-tablero.getCasillas().size();
            j.setDinero(j.getDinero()+200);
        }
        j.setPosicion(nuevaPosicion);
        return nuevaPosicion;
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

