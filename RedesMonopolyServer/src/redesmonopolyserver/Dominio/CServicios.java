package redesmonopolyserver.Dominio;
import java.io.Serializable;
import redesmonopolyserver.Comunicacion.Servidor;

public class CServicios extends Casilla implements Serializable{
    private int propietario;
    String tarjeta;

    public CServicios(int propietario, String nombre, int posJugadorX, int posJUgadorY, String tarjeta) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
        this.tarjeta=tarjeta;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
    public Servicio getServicio(Tablero t){
        for(Servicio s:t.getServicios()){
            System.out.println(s.getNombre()+this.getNombre());
            if(s.getNombre().substring(11).equals(this.getNombre().substring(11))) return s;
        }
        return null;
    }
    
    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        if(!jugador.isPerdio()){
            //Propietario de la casilla actual
            Jugador dueno = tablero.getJugadores().get(this.propietario);
            if(this.propietario < 0){
                System.out.println(this.getNombre());
                servidor.mandarPosibleCompra(jugador, "Servicio", this.getServicio(tablero).getNombre());
                servidor.esperar();
            }
            else if(tablero.getJugadores().indexOf(jugador) != this.propietario && !dueno.isCarcel()){
                int montoBase = tablero.getDado1() + tablero.getDado2();
                int montoFinal;
                //Servicio de electricidad
                CServicios electricidad = (CServicios) tablero.getCasillas().get(12); 
                //Servicio de agua
                CServicios agua = (CServicios) tablero.getCasillas().get(28); 

                if(electricidad.getPropietario() == agua.getPropietario()){
                    if(jugador.getDinero() - 10 * montoBase <= 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                        jugador.setPerdio(true);
                    }
                    else{
                        montoFinal = 10 * montoBase;
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                    }
                    servidor.mandarNotificacion(jugador, "Pagas dinero por alquiler", "Pagas " + montoFinal + "$ a " + dueno.getNombre() + " por alquiler de Servicios");
                    servidor.mandarNotificacion(dueno, "Recibes dinero por alquiler", "Recibes " + montoFinal + "$ de " + jugador.getNombre() + " por alquiler de Servicios");
                }
                else{
                    if(jugador.getDinero() - 4 * montoBase <= 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                        jugador.setPerdio(true);
                    }
                    else{
                        montoFinal = 4 * montoBase;
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                    }
                    servidor.mandarNotificacion(jugador, "Pago de alquiler", "Haz caido en " + this.getNombre() + ". \n Pagas " + montoFinal + "$ a " + dueno.getNombre());
                    servidor.mandarNotificacion(dueno, "Cobro por alquiler", jugador.getNombre() + " ha caido en " + this.getNombre() + ". \nCobras " + montoFinal + "$");
                }
            }
        }
    }
}
