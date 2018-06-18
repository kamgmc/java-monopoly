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
        if(m.tipo==0){
            //Se envio una actualizacion del tablero
            tablero.setJson(m.tablero.toString());
            if(pantalla!=null) pantalla.actualizarTablero(tablero);
        }
        if(m.tipo==1){
            //Se envio un 
            if(pantalla!=null) pantalla.mostrarNotificacion(m.titulo, m.mensaje);
            System.out.print("El mensaje es: "+m.mensaje);
        }
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
