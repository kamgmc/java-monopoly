package redesmonopolyserver.Dominio;

public abstract class Carta {
    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public abstract void Efecto();
    
}
