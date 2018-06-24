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
import redesmonopolyserver.Dominio.CFerrocarril;
import redesmonopolyserver.Dominio.CPropiedad;
import redesmonopolyserver.Dominio.CServicios;
import redesmonopolyserver.Dominio.Carta;
import redesmonopolyserver.Dominio.Casilla;
import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;
import redesmonopolyserver.Persistencia.Generador;
import redesmonopolyserver.Persistencia.Usuario;
public class Servidor {
        ServerSocket ss;
        ArrayList<ConexionUsuario> conexiones;
        Tablero tablero;
        Socket socket;
        int numJugadores = 2;
        boolean esperando;
    private int contadorTurnos;
        
    public Servidor() throws IOException {
        contadorTurnos =0;
        conexiones = new ArrayList<ConexionUsuario>();
        System.out.print("Inicializando servidor... ");
        tablero = new Tablero();
        Generador.GenerarCasillas(tablero);
        ss = new ServerSocket(10578);
        System.out.println("\tConexion realizada");
        while (conexiones.size()<numJugadores) {
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
    
    public void mandarIngresoExitoso(ConexionUsuario c){
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(2,"Ingresaste Exitosamente",""));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandarErrorIngreso(ConexionUsuario c, String mensaje){
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(3,"Error",mensaje));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandarErrorRegistro(ConexionUsuario c, String mensaje){
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(4,"Error",mensaje));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandarRegistroExitoso(ConexionUsuario c){
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(5,"Registro Exitoso",""));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandarPosibleCompra(Jugador j,String tipo, String propiedad){
        int pos = tablero.obtenerJugador(j.getNombre());
        ConexionUsuario c = this.conexiones.get(pos);
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(6,tipo,propiedad));
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
                    mandarNotificacion(tablero.getJugadores().get(cont),"Turno","Es tu turno!");
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
    
    public void mandarActualizacion(Solicitud s, ConexionUsuario c){
        try {
            c.getDos().flush();
            c.getDos().writeObject(new Mensaje(7,"Tablero Actualizado",tablero));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void agregarJugador(Solicitud s, ConexionUsuario c){
        Jugador j = new Jugador(s.getJugador(),"localhost",0);
            j.setCodigo(conexiones.indexOf(c));
            tablero.getJugadores().add(j);
            System.out.println("Se creo el usuario: "+j.getNombre());
            if(tablero.getJugadores().size()==numJugadores){
                long seed = System.nanoTime();
                tablero.acomodarJugadores();
                Collections.shuffle(conexiones,new Random(seed));
                Collections.shuffle(tablero.getJugadores(),new Random(seed));
                tablero.asignarUsuarios();
                mandarTablero(0);
            }
    }
    
    public void moverJugador(Solicitud s, ConexionUsuario c){
        System.out.println("Me solicitaron moverme");
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
                contadorTurnos = 0;
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
    
    public void comprar(Solicitud s, ConexionUsuario c){
        int jugador = tablero.obtenerJugador(s.jugador);
        Jugador j = tablero.getJugadores().get(jugador);
        int pos = tablero.posPropiedad(s.nombrePropiedad);
        Casilla casilla = tablero.getCasillas().get(pos);
        
        if(casilla instanceof CPropiedad){
            ((CPropiedad)casilla).setPropietario(jugador);
            j.setDinero(j.getDinero() - ((CPropiedad) casilla).getPropiedad(tablero).getPrecioCompra());
            mandarNotificacion(j,"Propiedad Adquirida",casilla.getNombre());
        }
        if(casilla instanceof CFerrocarril){
            ((CFerrocarril)casilla).setPropietario(jugador);
            j.setDinero(j.getDinero() - ((CFerrocarril) casilla).getFerrocarril(tablero).getPrecio());
            mandarNotificacion(j,"Ferrocarril Adquirido",casilla.getNombre());
        }
        if(casilla instanceof CServicios){
            ((CServicios)casilla).setPropietario(jugador);
            j.setDinero(j.getDinero() - ((CServicios) casilla).getServicio(tablero).getPrecio());
            mandarNotificacion(j,"Ferrocarril Adquirido",casilla.getNombre());
        }
        
    }
    
    public void intentarLoguear(Solicitud s, ConexionUsuario c){
        Usuario usuario = Usuario.fromJSON(s.getJugador());
            Usuario u = Usuario.obtenerUsuario(usuario.getUsername());
            if(u!=null){
                if(u.getPassword().equals(usuario.getPassword()))
                    mandarIngresoExitoso(c);
                else mandarErrorIngreso(c,"Clave incorrecta");
            }
            else mandarErrorIngreso(c,"Nombre de usuario no encontrado");    
    }
    
    public void intentarRegistrarse(Solicitud s, ConexionUsuario c){
        Usuario usuario = Usuario.fromJSON(s.getJugador());
            Usuario u = Usuario.obtenerUsuario(usuario.getUsername());
            if(u==null){
                usuario.guardarUsuario();
                mandarRegistroExitoso(c);
            }
            else mandarErrorRegistro(c,"El nombre de usuario ya existe");    
    }
    
    public void procesarSolicitud(Solicitud s, ConexionUsuario c){
        System.out.println("Solicitud: nombre - "+s.jugador+" tipo - "+s.tipo);
        if(s.tipo==0){
            // El jugador solicito unirse a la partida
            agregarJugador(s,c);
        }  
        else if(s.tipo==1){
            // El jugador solicito moverse
            moverJugador(s,c);

            }
        else if(s.tipo==2){
            // EL jugador pidio el tablero
            mandarActualizacion(s,c);
        }
        else if (s.tipo==3){
            // El jugador intento loguearse
            intentarLoguear(s,c);
        }
        else if (s.tipo==4){
            // El jugador intento registrarse
            intentarRegistrarse(s,c);
        }
        else if (s.tipo==5){
            comprar(s,c);
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
            mandarNotificacion(j,"Salida","Pasaste por la salida, cobra: $200");
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
                    s.procesarSolicitud(sol,this.c);
                    System.out.println("Solicitud Recibida");
                } catch (IOException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConexionUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void esperar(){
        try {
                
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void salirCarcelTarjeta(Tablero tablero, Jugador jugador){
        if(!jugador.isPerdio()){
            if(jugador.getCarcelLibre() > 0){
                jugador.setCarcelLibre(jugador.getCarcelLibre() - 1);
                jugador.setCarcel(false);
                this.mandarNotificacion(jugador, "Sales Gratis de la Carcel", "Haz usado tu carta para salir de la carcel gratis.");
            }
            else
                this.mandarNotificacion(jugador, "Sigues en la carcel", "No tienes cartas para salir de la carcel.");
        }
    }
    
    public void salirCarcelPagando(Tablero tablero, Jugador jugador){
        if(!jugador.isPerdio()){
            if(jugador.getDinero() - 50 <= 0){
                jugador.setDinero(0);
                jugador.setCarcel(false);
                jugador.setPerdio(true);
                this.mandarNotificacion(jugador, "Pago de Fianza", "No haz tenido suficiente dinero para pagar la carcel.");
            }
            else{
                jugador.setDinero(jugador.getDinero() - 50);
                jugador.setCarcel(false);
                this.mandarNotificacion(jugador, "Pagas Fianza", "Haz salido de la carcel pagando la fianza.");
            }
        }
    }
    
    public void salirCarcelDados(Tablero tablero, Jugador jugador){
        if(!jugador.isPerdio()){
            int posFinal = 0;
            tablero.setDado1((int)(1+Math.random()*6));
            tablero.setDado2((int)(1+Math.random()*6));
            if(tablero.getDado1() == tablero.getDado2()){
                jugador.setCarcel(false);
                this.mandarNotificacion(jugador, "Sales de la Carcel", "Haz sacado doble, puedes salir de la carcel.");
                for(int i=0;i<tablero.getDado1()+tablero.getDado2();i++){
                    posFinal = mover(jugador);
                    mandarTablero(-1);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tablero.getCasillas().get(posFinal).alLlegar(tablero, jugador, this);
            }
            else
                this.mandarNotificacion(jugador, "Sigues en la Carcel", "No haz sacado doble, seguiras en la carcel.");
        }
    }
}

