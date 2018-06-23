package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;
/**
 *
 * @author kamgm
 */
public class CPropiedad extends Casilla implements Serializable{
    
    private int numeroCasas;
    private int numeroHoteles;
    private int propietario;
    private int posCasasX;
    private int posCasasY;

    public CPropiedad(int posCasasX, int posCasasY,String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.propietario = -1;
        this.posCasasX = posCasasX;
        this.posCasasY = posCasasY;
    }

    public int getNumeroCasas() {
        return numeroCasas;
    }

    public void setNumeroCasas(int numeroCasas) {
        this.numeroCasas = numeroCasas;
    }

    public int getNumeroHoteles() {
        return numeroHoteles;
    }

    public void setNumeroHoteles(int numeroHoteles) {
        this.numeroHoteles = numeroHoteles;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public int getPosCasasX() {
        return posCasasX;
    }

    public void setPosCasasX(int posCasasX) {
        this.posCasasX = posCasasX;
    }

    public int getPosCasasY() {
        return posCasasY;
    }

    public void setPosCasasY(int posCasasY) {
        this.posCasasY = posCasasY;
    }
    
    public Propiedad getPropiedad(Tablero t){
        for(Propiedad p : t.getPropiedades()){
            if(p.getNombre().equals(this.getNombre())) return p;
        }
        return null;
    }

    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        if(this.propietario < 0){
            //Preguntar si quiere comprar la propiedad
        }
        else{
            //Pagar la renta al propietario
            servidor.mandarNotificacion(jugador, "Pago de alquiler", "Haz caido en ... debes pagar ...$");
        }
    }
}
