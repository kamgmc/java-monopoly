
package redesmonopolyserver.Dominio;

import java.io.Serializable;

public class Servicio implements Serializable{
    private String nombre;
    private int unServicio;
    private int dosServicios;
    private int precio;

    public Servicio(String nombre, int unServicio, int dosServicios) {
        this.nombre = nombre;
        this.unServicio = unServicio;
        this.dosServicios = dosServicios;
        this.precio = 150;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnServicio() {
        return unServicio;
    }

    public void setUnServicio(int unServicio) {
        this.unServicio = unServicio;
    }

    public int getDosServicios() {
        return dosServicios;
    }

    public void setDosServicios(int dosServicios) {
        this.dosServicios = dosServicios;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
