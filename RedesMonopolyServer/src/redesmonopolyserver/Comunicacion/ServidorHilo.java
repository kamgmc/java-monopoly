/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesmonopolyserver.Comunicacion;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServidorHilo extends Thread {
    private Socket socket;
    private ObjectOutputStream dos;
    private ObjectInputStream dis;
    private int idSessio;
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        while (true){
            Mensaje m;
            try {
                m = (Mensaje) dis.readObject();
                if(m.tipo==1){
                    System.out.println("El cliente con idSesion "+this.idSessio+" saluda");
                    dos.writeObject(new Mensaje(2,"adios"));
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
;
        };
        desconnectar();
    }
}
