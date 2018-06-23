package redesmonopolyserver.Dominio;
import java.io.Serializable;
import java.util.ArrayList;
import redesmonopolyserver.Comunicacion.Servidor;

public class CFerrocarril extends Casilla implements Serializable{
    
    private int propietario;

    public CFerrocarril(int propietario, String nombre, int posJugadorX, int posJUgadorY) {
        super(nombre, posJugadorX, posJUgadorY);
        this.propietario = propietario;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
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
        if(this.propietario >= 0){
            //Propietario del ferrocarril
            Jugador dueno = tablero.getJugadores().get(this.propietario);
            
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
                    if(jugador.getDinero() - 25 < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = 25;
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 2:
                    if(jugador.getDinero() - 50 < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = 50;
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 3:
                    if(jugador.getDinero() - 100 < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
                    }
                    else{
                        montoFinal = 100;
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(jugador.getDinero() - montoFinal);
                    }
                    break;
                case 4:
                    if(jugador.getDinero() - 200 < 0){
                        montoFinal = jugador.getDinero();
                        dueno.setDinero(dueno.getDinero() + montoFinal);
                        jugador.setDinero(0);
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
            servidor.mandarNotificacion(jugador, "Pagas dinero por alquiler", "Pagas " + montoFinal + "$ a " + dueno.getNombre() + " por alquiler de Ferrocarril");
            servidor.mandarNotificacion(dueno, "Recibes dinero por alquiler", "Recibes " + montoFinal + "$ de " + jugador.getNombre() + " por alquiler de Ferrocarril");
        }
        else{
            servidor.mandarPosibleCompra(jugador, "Ferrocarril", this.getFerrocarril(tablero).getNombre());
            servidor.esperar();
        }
    }
}
