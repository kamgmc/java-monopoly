/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.*;
import redesmonopolyserver.Dominio.Carta;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Persistencia.Generador;
public class Servidor {
        ServerSocket ss;
        ArrayList<ConexionUsuario> conexiones;
        Tablero tablero;
        ArrayList<Carta> fortuna;
        ArrayList<Carta> arca;
        
    public Servidor() throws IOException {
        conexiones = new ArrayList<ConexionUsuario>();
        System.out.print("Inicializando servidor... ");
        try {
            tablero = new Tablero();
            Generador.GenerarCasillas(tablero);
            Generador.generarTarjetas(fortuna, arca);
            ss = new ServerSocket(10578);
            System.out.println("\tConexion realizada");
            int idSession = 0;
            while (conexiones.size()<4) {
                Socket socket;
                socket = ss.accept();
                ConexionUsuario c = new ConexionUsuario(socket);
                System.out.println("Se ha conectado un usuario: "+socket);
                try {
                    Solicitud s = (Solicitud) c.getDis().readObject();
                    if(s.tipo==0){
                        conexiones.add(c);
                        Jugador j = new Jugador(s.getJugador(),"localhost",tablero.getCasillas().get(0));
                        tablero.getJugadores().add(j);
                        System.out.println("Se creo el usuario: "+j.getNombre());
                        Mensaje m = new Mensaje(1,"Tablero",tablero);
                        c.getDos().writeObject(m);
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            long seed = System.nanoTime();
            Collections.shuffle(conexiones,new Random(seed));
            Collections.shuffle(tablero.getJugadores(),new Random(seed));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}