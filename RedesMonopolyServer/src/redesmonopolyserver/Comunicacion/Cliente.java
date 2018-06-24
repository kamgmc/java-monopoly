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
import redesmonopolyserver.Dominio.Casilla;
import redesmonopolyserver.Dominio.Ferrocarril;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Propiedad;
import redesmonopolyserver.Dominio.Servicio;
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Pantallas.PantallaJugadorPrincipal;
import redesmonopolyserver.Persistencia.Generador;
import redesmonopolyserver.Persistencia.Usuario;

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
    private String tipoCompra;
    private String compra;
    
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
    
    
    public void cerrarConexion(){
        try {
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void enviarJugador(){
        System.out.print("Hola soy: "+nombre);
        Solicitud s = new Solicitud(this.nombre,0);
        try {
            dos.flush();
            dos.writeObject(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void solicitarIngreso(Usuario usuario){
        Solicitud s = new Solicitud(usuario.toJSON(),3);
        this.nombre=usuario.getUsername();
        try {
            dos.flush();
            dos.writeObject(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void solicitarRegistro(Usuario usuario){
        Solicitud s = new Solicitud(usuario.toJSON(),4);
        this.nombre=usuario.getUsername();
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
            System.out.println("Has solicitado moverte");
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
    
    public void solicitarCompra(){
        Solicitud s = new Solicitud(nombre,5);
        s.setNombrePropiedad(compra);
        try {
            dos.flush();
            dos.writeObject(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void solicitarSalir(String tipo){
        Solicitud s = new Solicitud(nombre,6);
        s.setNombrePropiedad(tipo);
        try {
            dos.flush();
            dos.writeObject(s);
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
            //Se envio una notificacion
            if(pantalla!=null) pantalla.mostrarNotificacion(m.titulo, m.mensaje);
        }
        if(m.tipo==2){
            //Se logueo exitosamente en el sistema
            if(pantalla!=null) pantalla.iniciarJuego();
        }
        if(m.tipo==3){
            // Llego un error de logueo
            if(pantalla!=null) pantalla.mostrarErrorLogin(m.mensaje);
        }
        if(m.tipo==4){
            // Llego un error de registro
            if(pantalla!=null) pantalla.mostrarErrorRegistro(m.mensaje);
        }
        if(m.tipo==5){
            // Se registro un usuario
            if(pantalla!=null) pantalla.finalizarRegistro();
        }
        if(m.tipo==6){
            // La casilla se encuentra disponible para compra
            habilitarCompra(m);
        }
    }
    
    public void habilitarCompra(Mensaje m){
        if(m.titulo.equals("Propiedad")){
            Propiedad p = tablero.buscarPropiedad(m.mensaje);
            if(pantalla!=null) pantalla.mostrarCompra("Propiedad", p.getNombre(), p.getPrecioCompra());
            tipoCompra="Propiedad";
            compra=p.getNombre();
        }
        else if(m.titulo.equals("Ferrocarril")){
            Ferrocarril f = tablero.buscarFerrocarril(m.mensaje);
            if(pantalla!=null) pantalla.mostrarCompra("Ferrocarril", f.getNombre(), f.getPrecio());
            tipoCompra="Ferrocarril";
            compra=f.getNombre();
        }
        else if(m.titulo.equals("Servicio")){
            Servicio s = tablero.buscarServicio(m.mensaje);
            if(pantalla!=null) pantalla.mostrarCompra("Servicio", s.getNombre(), s.getPrecio());
            tipoCompra="Servicio";
            compra=s.getNombre();
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
