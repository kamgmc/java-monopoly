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
            servidor.mandarPosibleCompra(jugador, "Propiedad", this.getPropiedad(tablero).getNombre());
            servidor.esperar();
        }
        else{
            //Propietario de la propiedad
            Jugador dueno = tablero.getJugadores().get(this.propietario);
            int montoFinal = 0;
            
            switch (this.numeroCasas) {
                case 0:
                    if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getAlquiler() < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = tablero.buscarPropiedad(this.getNombre()).getAlquiler();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 1:
                    if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getUnaCasa() < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = tablero.buscarPropiedad(this.getNombre()).getUnaCasa();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 2:
                    if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getDosCasas() < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = tablero.buscarPropiedad(this.getNombre()).getDosCasas();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 3:
                    if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getTresCasas() < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = tablero.buscarPropiedad(this.getNombre()).getTresCasas();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 4:
                    if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getCuatroCasas() < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = tablero.buscarPropiedad(this.getNombre()).getCuatroCasas();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                default:
                    break;
            }
            //En caso de que haya un hotel en la propiedad
            if(this.numeroHoteles > 0){
                if(jugador.getDinero() - tablero.buscarPropiedad(this.getNombre()).getConHotel() < 0){
                    montoFinal = jugador.getDinero();
                    dueno.setDinero(dueno.getDinero() + montoFinal);
                    jugador.setDinero(0);
                }
                else{
                    montoFinal = tablero.buscarPropiedad(this.getNombre()).getConHotel();
                    dueno.setDinero(dueno.getDinero() + montoFinal);
                    jugador.setDinero(jugador.getDinero() - montoFinal);
                }
            }
            servidor.mandarNotificacion(jugador, "Pago de alquiler", "Haz caido en " + this.getNombre() + " debes pagar " + montoFinal + "$ a " + dueno.getNombre());
            servidor.mandarNotificacion(dueno, "Cobro por alquiler", jugador.getNombre() + " ha caido en " + this.getNombre() + ". Cobras " + montoFinal + "$");
        }
    }
}
