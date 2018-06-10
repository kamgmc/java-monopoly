package redesmonopolyserver.Dominio;
/**
 *
 * @author kamgm
 */
public class CPropiedad extends Casilla{
    private int numeroCasas;
    private int numeroHoteles;
    private int propietario;
    private int posCasasX;
    private int posCasasY;

    public CPropiedad(String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.propietario = -1;
        this.posCasasX = posJugadorX-9;
        this.posCasasY = posJUgadorY-40;
    }

    

    @Override
    public void alSalir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alLlegar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
