
package redesmonopolyserver.Dominio;

public class CServicios extends Casilla{
    private int propietario;

    public CServicios(int propietario, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
    }

    @Override
    public void alSalir() {
       }

    @Override
    public void alLlegar() {
       }
}
