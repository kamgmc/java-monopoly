package redesmonopolyserver.Dominio;

public class CImpuesto extends Casilla{
    private int monto;

    public CImpuesto(int monto, String nombre) {
        super(nombre);
        this.monto = monto;
    }

    @Override
    public void alSalir() {
        }

    @Override
    public void alLlegar() {
       }
}
