package redesmonopolyserver;

import redesmonopolyserver.Dominio.Jugador;
import redesmonopolyserver.Dominio.Tablero;

public class ControladorJugadores {

public void AgregarJugador(Tablero tablero,Jugador jugador){
    //Inicializar jugador con la cantidad de dinero
    jugador.setDinero(1500);
    tablero.getJugadores().add(jugador);
};

public void asginarJugadores(Tablero tablero){
    tablero.getJugadores().sort(null);
    for(int i=0; i<tablero.getJugadores().size();i++){
        tablero.getJugadores().get(i).setCodigo(i);
    }
}


}
