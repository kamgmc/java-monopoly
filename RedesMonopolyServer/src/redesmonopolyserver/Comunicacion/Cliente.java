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
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Pantallas.PantallaJugadorPrincipal;

public class Cliente extends Thread {
    protected Socket sk;
    protected ObjectOutputStream dos;
    protected ObjectInputStream dis;
    private String ip;
    private String nombre;
    private PantallaJugadorPrincipal pantalla;
    public Cliente(String ip,String nombre) {
        this.ip=ip;
        this.nombre = nombre;
        try {
        sk = new Socket("127.0.0.1", 10578);
        dos = new ObjectOutputStream(sk.getOutputStream());
        dis = new ObjectInputStream(sk.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    @Override
    public void run() {
        enviarJugador(nombre);
        try {
            Mensaje m = (Mensaje) dis.readObject();
            procesarRespuesta(m);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void enviarMensaje(Mensaje m){
        try {
            System.out.println(ip + " envía saludo");
            dos.writeObject(m);
            Mensaje respuesta;
            respuesta = (Mensaje) dis.readObject();
            System.out.println(ip + " Servidor devuelve saludo: " + respuesta.mensaje);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarJugador(String nombre){
        Solicitud s = new Solicitud(nombre,0);
        try {
            dos.writeObject(s);
            Mensaje respuesta;
            respuesta = (Mensaje) dis.readObject();
            procesarRespuesta(respuesta);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public void procesarRespuesta(Mensaje m){
        System.out.print("Algo me mandaron");
        pantalla.setTablero(m.tablero);
        pantalla.actualizarTablero();
        
    
    }

    public PantallaJugadorPrincipal getPantalla() {
        return pantalla;
    }

    public void setPantalla(PantallaJugadorPrincipal pantalla) {
        this.pantalla = pantalla;
    }
    
    
}
