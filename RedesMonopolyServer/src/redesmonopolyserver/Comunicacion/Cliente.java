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

public class Cliente extends Thread {
    protected Socket sk;
    protected ObjectOutputStream dos;
    protected ObjectInputStream dis;
    private String ip;
    public Cliente(String ip) {
        this.ip=ip;
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
        enviarMensaje(new Mensaje(1,"Hola"));
        
        
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
}
