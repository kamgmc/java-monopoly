package redesmonopolyserver.Dominio;
import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Comunicacion.Servidor;

public class CFerrocarril extends Casilla implements Serializable{
    
    private int propietario;
    String tarjeta;

    public CFerrocarril(int propietario, String nombre, int posJugadorX, int posJUgadorY,String tarjeta) {
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
    
    public Ferrocarril getFerrocarril(Tablero t){
        for (Ferrocarril f : t.getFerrocarriles()){
            if(f.getNombre().equals(this.getNombre())) return f;
        }
        return null;
    }

    @Override
    public void alSalir() {}

    @Override
    public void alLlegar(Tablero tablero, Jugador jugador, Servidor servidor) {
        //Si aun no ha perdido
        if(!jugador.isPerdio()){
            //Propietario del ferrocarril
            Jugador dueno = tablero.getJugadores().get(this.propietario);
            
            if(this.propietario < 0){
                servidor.mandarPosibleCompra(jugador, "Ferrocarril", this.getFerrocarril(tablero).getNombre());
                servidor.esperar();
            }
            else if(tablero.getJugadores().indexOf(jugador) != this.propietario && !dueno.isCarcel()){
                //Trenes
                ArrayList<CFerrocarril> trenes = new ArrayList();
                trenes.add((CFerrocarril) tablero.getCasillas().get(5));
                trenes.add((CFerrocarril) tablero.getCasillas().get(15));
                trenes.add((CFerrocarril) tablero.getCasillas().get(25));
                trenes.add((CFerrocarril) tablero.getCasillas().get(35));

                //Cantidad de Ferrocarriles que posee el propietario
                int cont = 0;
                for(int i = 0; i < trenes.size(); i++){
                    if(this.propietario == trenes.get(i).getPropietario()){
                        cont++;
                    }
                }

                int montoFinal = 0;

                //Pago dependiendo de la cantidad de trenes
                switch (cont) {
                    case 1:
                        if(jugador.getDinero() - 25 <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = 25;
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 2:
                        if(jugador.getDinero() - 50 <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = 50;
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 3:
                        if(jugador.getDinero() - 100 <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = 100;
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    case 4:
                        if(jugador.getDinero() - 200 <= 0){
                            montoFinal = jugador.getDinero();
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(0);
                            jugador.setPerdio(true);
                        }
                        else{
                            montoFinal = 200;
                            dueno.setDinero(dueno.getDinero() + montoFinal);
                            jugador.setDinero(jugador.getDinero() - montoFinal);
                        }
                        break;
                    default:
                        break;
                }
                servidor.mandarNotificacion(jugador, "Pago de alquiler", "Haz caido en " + this.getNombre() + ". \n Pagas " + montoFinal + "$ a " + dueno.getNombre());
                servidor.mandarNotificacion(dueno, "Cobro por alquiler", jugador.getNombre() + " ha caido en " + this.getNombre() + ". \nCobras " + montoFinal + "$");
            }
        }
    }
}
