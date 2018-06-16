package redesmonopolyserver.Dominio;

import java.io.Serializable;

public class Jugador implements Serializable{

    public Jugador(String nombre, String ip, int posicion) {
        this.nombre = nombre;
        this.dinero = 0;
        this.carcelLibre = 0;
        this.ip = ip;
        this.posicion = posicion;
        
    }
 
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getCarcelLibre() {
        return carcelLibre;
    }

    public void setCarcelLibre(int carcelLibre) {
        this.carcelLibre = carcelLibre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

  
    
    
    
    
    private int codigo;
    private String nombre;
    private int dinero;
    private int carcelLibre;
    private int icono;
    private String ip;
    private int posicion;
}
