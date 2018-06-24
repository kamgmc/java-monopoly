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
            if(s.getNombre().equals(this.getNombre())) return s;
        }
        return null;
    }
    
    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        if(!jugador.isPerdio()){
            //Si el servicio ya esta comprado
            if(this.propietario >= 0){
                int montoBase = tablero.getDado1() + tablero.getDado2();
                int montoFinal;
                //Servicio de electricidad
                CServicios electricidad = (CServicios) tablero.getCasillas().get(12); 
                //Servicio de agua
                CServicios agua = (CServicios) tablero.getCasillas().get(28); 
                //Propietario de la casilla actual
                Jugador dueno = tablero.getJugadores().get(this.propietario);

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
                    servidor.mandarNotificacion(jugador, "Pagas dinero por alquiler", "Pagas " + montoFinal + "$ a " + dueno.getNombre() + " por alquiler de Servicios");
                    servidor.mandarNotificacion(dueno, "Recibes dinero por alquiler", "Recibes " + montoFinal + "$ de " + jugador.getNombre() + " por alquiler de Servicios");
                }
            }
            else{
                servidor.mandarPosibleCompra(jugador, "Servicio", this.getServicio(tablero).getNombre());
                servidor.esperar();
            }
        }
    }
}
