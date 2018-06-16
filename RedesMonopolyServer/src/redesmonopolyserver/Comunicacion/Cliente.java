/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Pantallas.PantallaJugadorPrincipal;
import redesmonopolyserver.Persistencia.Generador;

public class Cliente{
    protected Socket sk;
    protected ObjectOutputStream dos;
    protected ObjectInputStream dis;
    private String ip;
    private String nombre;
    private int posJugador;
    private PantallaJugadorPrincipal pantalla;
    private Tablero tablero;
    public boolean enUso=false;
    
    public Cliente(String ip,String nombre,String server, int puerto) {
        this.ip=ip;
        this.nombre = nombre;
        this.posJugador=0;
        tablero = new Tablero();
        Generador.GenerarCasillas(tablero);
        try {
        sk = new Socket(server, puerto);
        dos = new ObjectOutputStream(sk.getOutputStream());
        dis = new ObjectInputStream(sk.getInputStream());
        new Thread(new Listener(this)).start();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void conectarse() {
        System.out.println("Hola soy "+nombre);
        enviarJugador(nombre);
    }
    
    public void cerrarConexion(){
        try {
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void enviarJugador(String nombre){
        Solicitud s = new Solicitud(nombre,0);
        try {
            dos.flush();
            dos.writeObject(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void solicitarMoverse(){
        Solicitud s = new Solicitud(nombre,1);
        try {
            dos.flush();
            dos.writeObject(s);
            System.out.println("Haz solicitado moverte");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void solicitarTablero(){
        Solicitud s = new Solicitud(nombre,2);
        try {
            dos.flush();
            dos.writeObject(s);
            System.out.println("Haz solicitado el tablero");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
       
    public void procesarRespuesta(Mensaje m){
            System.out.println("Procesando Respuesta");
            tablero.actualizar(m.tablero);
            tablero.actualizarUsuarios(m.pos1, m.pos2, m.pos3, m.pos4);
            if(pantalla!=null) pantalla.actualizarTablero(tablero);
    }
            
    public PantallaJugadorPrincipal getPantalla() {
        return pantalla;
    }

    public void setPantalla(PantallaJugadorPrincipal pantalla) {
        this.pantalla = pantalla;
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosJugador() {
        return posJugador;
    }

    public void setPosJugador(int posJugador) {
        this.posJugador = posJugador;
    }
    
    public void actualizarPosicion(){
        int i=0;
        for(Jugador j: tablero.getJugadores()){ 
            if(j.getNombre().equals(this.nombre)) this.posJugador=i;
            i++;
        }
        
    }
    
    public class Listener implements Runnable{
        private Cliente c;

        public Listener(Cliente c) {
            this.c = c;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("Esperando el Tablero");
                    Mensaje m = (Mensaje) c.dis.readObject();
                    m.tablero.imprimirTablero();
                    c.procesarRespuesta(m);
                    System.out.println("Tablero Recibido");
                } catch (IOException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
