package redesmonopolyserver.Dominio;

public class CCarta extends Casilla{
    private String tipo;

    public CCarta(String tipo, String nombre) {
        super(nombre);
        this.tipo = tipo;
    }

    @Override
    public void alSalir() {
     }

    @Override
    public void alLlegar() {
        }
    
}
