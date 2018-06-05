package redesmonopolyserver.Dominio;


/**
 *
 * @author kamgm
 */
public abstract class Casilla {
    
    private String nombre;

    public Casilla(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract void alSalir();
    public abstract void alLlegar();
}
