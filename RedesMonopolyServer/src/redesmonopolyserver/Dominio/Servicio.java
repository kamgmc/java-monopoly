
package redesmonopolyserver.Dominio;

import java.io.Serializable;

public class Servicio implements Serializable{
    private String nombre;
    private int unServicio;
    private int dosServicios;

    public Servicio(String nombre, int unServicio, int dosServicios) {
        this.nombre = nombre;
        this.unServicio = unServicio;
        this.dosServicios = dosServicios;
    }
}
