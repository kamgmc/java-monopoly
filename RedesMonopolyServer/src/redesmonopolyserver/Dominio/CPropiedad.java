package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CPropiedad extends Casilla implements Serializable{
    
    private int numeroCasas;
    private int numeroHoteles;
    private int propietario;
    private int posCasasX;
    private int posCasasY;
    String tarjeta;

    public CPropiedad(int posCasasX, int posCasasY,String nombre, int posJugadorX, int posJUgadorY, String tarjeta) {
        super(nombre, posJugadorX, posJUgadorY);
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.propietario = -1;
        this.posCasasX = posCasasX;
        this.posCasasY = posCasasY;
        this.tarjeta=tarjeta;
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

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
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
        
        if(!jugador.isPerdio()){
            //Propietario de la propiedad
                Jugador dueno = tablero.getJugadores().get(this.propietario);
            if(this.propietario < 0){
                servidor.mandarPosibleCompra(jugador, "Propiedad", this.getPropiedad(tablero).getNombre());
                servidor.esperar();
            }
            else if(tablero.getJugadores().indexOf(jugador) != this.propietario && !dueno.isCarcel()){
                int montoFinal = 0;
                Propiedad propiedad = tablero.buscarPropiedad(this.getNombre());

                switch (this.numeroCasas) {
                    case 0:
                        if(jugador.getDinero() - propiedad.getAlquiler() <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = propiedad.getAlquiler();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 1:
                        if(jugador.getDinero() - propiedad.getUnaCasa() <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = propiedad.getUnaCasa();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 2:
                        if(jugador.getDinero() - propiedad.getDosCasas() <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = propiedad.getDosCasas();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 3:
                        if(jugador.getDinero() - propiedad.getTresCasas() <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = propiedad.getTresCasas();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 4:
                        if(jugador.getDinero() - propiedad.getCuatroCasas() <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = propiedad.getCuatroCasas();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    default:
                        break;
                }
                //En caso de que haya un hotel en la propiedad
                if(this.numeroHoteles > 0){
                    if(jugador.getDinero() - propiedad.getConHotel() <= 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                        jugador.setPerdio(true);
                    }
                    else{
                        montoFinal = propiedad.getConHotel();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                }
                servidor.mandarNotificacion(jugador, "Pago de alquiler", "Haz caido en " + this.getNombre() + ". \n Pagas " + montoFinal + "$ a " + dueno.getNombre());
                servidor.mandarNotificacion(dueno, "Cobro por alquiler", jugador.getNombre() + " ha caido en " + this.getNombre() + ". \nCobras " + montoFinal + "$");
            }
        }
    }
}
