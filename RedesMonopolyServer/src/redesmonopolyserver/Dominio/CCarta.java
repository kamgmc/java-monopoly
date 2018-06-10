package redesmonopolyserver.Dominio;

public class CCarta extends Casilla{
    private String tipo;

    public CCarta(String tipo, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.tipo = tipo;
    }

    

    @Override
    public void alSalir() {
     }

    @Override
    public void alLlegar() {
        }
    
}
