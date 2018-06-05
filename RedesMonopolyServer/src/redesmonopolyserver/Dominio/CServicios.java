
package redesmonopolyserver.Dominio;

public class CServicios extends Casilla{
    private int propietario;

    public CServicios(int propietario, String nombre) {
        super(nombre);
        this.propietario = propietario;
    }

    @Override
    public void alSalir() {
       }

    @Override
    public void alLlegar() {
       }
}
