package redesmonopolyserver.Dominio;
/**
 *
 * @author kamgm
 */
public class CPropiedad extends Casilla{
    private int numeroCasas;
    private int numeroHoteles;
    private int propietario;

    public CPropiedad(String nombre, int numeroCasas, int numeroHoteles, int propietario) {
        super(nombre);
        this.numeroCasas = numeroCasas;
        this.numeroHoteles = numeroHoteles;
        this.propietario = propietario;
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
