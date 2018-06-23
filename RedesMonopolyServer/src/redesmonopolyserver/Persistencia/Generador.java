package redesmonopolyserver.Persistencia;

import java.util.ArrayList;
import java.util.Collections;
import redesmonopolyserver.Dominio.*;
import redesmonopolyserver.Dominio.Propiedad.Color;

/**
 *
 * @author kamgm
 */
public class Generador {
    public static void generarPropiedades(ArrayList<Propiedad> propiedades,ArrayList<Ferrocarril> ferrocarriles, ArrayList<Servicio> servicios){
        //PROPIEDADES
        propiedades.add(new Propiedad("Avenida Mediterraneo", Color.MARRON, 2, 60, 50, 50, 30, 10, 30, 90, 160, 250, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Baltica", Color.MARRON, 4, 60, 50, 50, 30, 20, 60, 180, 320, 450, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Oriental", Color.CELESTE, 6, 100, 50, 50, 50, 30, 90, 270, 400, 550, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Vermont", Color.CELESTE, 6, 100, 50, 50, 50, 30, 90, 270, 400, 550, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Connecticut", Color.CELESTE, 8, 120, 50, 50, 60, 40, 100, 300, 450, 600, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Plaza San Carlos", Color.FUXIA, 10, 140, 100, 100, 70, 50, 150, 450, 625, 750, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Status", Color.FUXIA, 10, 140, 100, 100, 70, 50, 150, 450, 625, 750, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Virginia", Color.FUXIA, 12, 160, 100, 100, 80, 60, 180, 500, 700, 900, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Plaza St. James", Color.NARANJA, 14, 180, 100, 100, 90, 70, 200, 550, 750, 950, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Tennessee", Color.NARANJA, 14, 180, 100, 100, 90, 70, 200, 550, 750, 950, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Nueva York", Color.NARANJA, 16, 200, 100, 100, 100, 80, 220, 600, 800, 1000, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Kentucky", Color.ROJO, 18, 220, 150, 150, 110, 90, 250, 700, 875, 1050, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Indiana", Color.ROJO, 18, 220, 150, 150, 110, 90, 250, 700, 875, 1050, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Illinois", Color.ROJO, 20, 240, 150, 150, 120, 100, 300, 750, 925, 1100, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Atlantico", Color.AMARILLO, 22, 260, 150, 150, 130, 110, 330, 800, 975, 1150, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Ventnor", Color.AMARILLO, 22, 260, 150, 150, 130, 110, 330, 800, 975, 1150, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Jardines Marvin", Color.AMARILLO, 24, 280, 150, 150, 140, 120, 360, 850, 1025, 1200, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Pacifico", Color.VERDE, 26, 300, 200, 200, 150, 130, 390, 900, 1100, 1275, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Carolina del Norte", Color.VERDE, 26, 300, 200, 200, 150, 130, 390, 900, 1100, 1275, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Avenida Pennsylvania", Color.VERDE, 28, 320, 200, 200, 160, 150, 450, 1000, 1200, 1400, 0, 0, 0, 0));
        propiedades.add(new Propiedad("Plaza Park", Color.AZUL, 35, 350, 200, 200, 175, 175, 500, 1100, 1300, 1500, 0, 0, 0, 0));
        propiedades.add(new Propiedad("El Muelle", Color.AZUL, 50, 400, 200, 200, 200, 200, 600, 1400, 1700, 2000, 0, 0, 0, 0));
        
        //FERROCARRILES
        ferrocarriles.add(new Ferrocarril("Lectura", 25, 50, 100, 200, 100));
        ferrocarriles.add(new Ferrocarril("Pennsylvania", 25, 50, 100, 200, 100));
        ferrocarriles.add(new Ferrocarril("B. & O.", 25, 50, 100, 200, 100));
        ferrocarriles.add(new Ferrocarril("Via Rapida", 25, 50, 100, 200, 100));
        
        //SEVICIOS
        servicios.add(new Servicio("Compania de Agua", 4, 10));
        servicios.add(new Servicio("Compania de Electricidad", 4, 10));
    }
    public static void generarTarjetas(ArrayList<Carta> fortuna, ArrayList<Carta> arca){
        //Mazo de Fortuna
        
        //Cartas de Movimiento
        fortuna.add(new CartaAvanzar("Avanza a la \"Salida\" (Cobra $200)", 0));
        fortuna.add(new CartaAvanzar("Avanza a Avenida Illinois, si pasas por la \"Salida\" Cobra $200.", 24));
        fortuna.add(new CartaAvanzar("Retrocede 3 espacios.", -3));
        fortuna.add(new CartaAvanzar("Avanza a Plaza St. James Si pasas por la \"Salida\" Cobra $200.", 16));
        fortuna.add(new CartaAvanzar("Avanza a El Muelle.", 39));
        //Movimientos dinamicos
        fortuna.add(new CartaAvanzar("Avanza al Servicio mas cercano.", 12, 28));
        fortuna.add(new CartaAvanzar("Avanza al Ferrocarril mas cercano.", 5, 15, 25, 35));
        fortuna.add(new CartaAvanzar("Avanza al Ferrocarril mas cercano.", 5, 15, 25, 35));
        fortuna.add(new CartaAvanzar("Haces un viaje a Ferrocarril \"Reading\" Si pasas por la \"Salida\" Cobra $200.", 5, 15, 25, 35));
        //Carta ir a la carcel
        fortuna.add(new CartaAvanzar("Vas a la Carcel.", 10));
        //Carta de Pago
        fortuna.add(new CartaPagar("Paga multa por exceso de velocidad $15", 15));
        //Carta de Pago a jugadores
        fortuna.add(new CartaPagarJugadores("Te han elegido presidente de la mesa directiva. Paga $50 a cada jugador.", 50));
        //Cartas de Cobro
        fortuna.add(new CartaCobrar("Liquidas tu prestamo para construccion. Cobra $150.", 150));
        fortuna.add(new CartaCobrar("El banco te paga un dividendo por $50.", 50));
        //Carta Salir de la carcel
        fortuna.add(new CartaSalirCarcel("Sales de la carcel gratis."));
        //Carta Pagos por casas
        fortuna.add(new CartaPagarPropiedades("Haces reparaciones en todas tus propiedades: por cada casa paga $25, por cada hotel paga $100.", 25, 100));
        //Barajar Cartas
        Collections.shuffle(fortuna);
        
        //Mazo de Arca Comunal
        
        //Cartas de Pago
        arca.add(new CartaPagar("Paga cuotas de hospital por $100", 100));
        arca.add(new CartaPagar("Paga cuotas de escuela por $50", 50));
        arca.add(new CartaPagar("Cuotas de medicas paga $50", 50));
        //Cartas de Movimiento
        arca.add(new CartaAvanzar("Vas a la Carcel", 10));
        arca.add(new CartaAvanzar("Avanza a la \"Salida\" (Cobra $200)", 0));
        //Carta de Cobro por jugador
        arca.add(new CartaCobrarJugadores("Es tu cumpleanos cada jugador te da $10.", 10));
        //Cartas de Cobro
        arca.add(new CartaCobrar("Te pagan seguro de vida. Cobra $100.", 100));
        arca.add(new CartaCobrar("Ganaste el segundo premio en un concurso de belleza. Cobra $10", 10));
        arca.add(new CartaCobrar("Devolucion de impuestos. Cobra $20.", 20));
        arca.add(new CartaCobrar("Recibes $25 por servicios de asesoria.", 25));
        arca.add(new CartaCobrar("Te pagan prima vacacional. Cobra $100.", 100));
        arca.add(new CartaCobrar("Por ventas de acciones ganas $50.", 50));
        arca.add(new CartaCobrar("Heredas $100.", 100));
        arca.add(new CartaCobrar("Error Bancario a tu favor. Cobra $200.", 200));
        //Carta Salir de la carcel
        arca.add(new CartaSalirCarcel("Sales de la carcel gratis."));
        //Carta Pagos por casas
        arca.add(new CartaPagarPropiedades("A pagar impuestos por la reparacion de las calles: $40 por cada casa, $115 por cada hotel.", 40, 115));
        //Barajar Cartas
        Collections.shuffle(arca);
    }
    
    public static void GenerarCasillas(Tablero t){
        int x;
        int y;
        int vx;
        int vy;
        int cx;
        int cy;
        CGo cg;
        CPropiedad cp;
        CCarta cc;
        CImpuesto ci;
        CFerrocarril cf;
        CCarcel cca;
        CServicios cs;
        CLibre cl;
        CVeACarcel cv;
        
        
        // Casilla de Go
        x=644;
        y=644;
        vx=-57;
        cg = new CGo("Go",x,y); 
        t.getCasillas().add(cg);
        
        // Primera fila de casillas
        x=567;
        y=656;
        cx=-9;
        cy=-40;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Mediterraneo",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cc = new CCarta("Arca","Arca Comunal 1",x,y);
        t.getCasillas().add(cc);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Baltica",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        ci = new CImpuesto(200,"Tax Income",x,y);
        t.getCasillas().add(ci);
        x+=vx;
        cf = new CFerrocarril(-1,"Lectura",x,y);
        t.getCasillas().add(cf);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Oriental",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cc = new CCarta("Fortuna","Fortuna 1",x,y);
        t.getCasillas().add(cc);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Vermont",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Connecticut",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        
        //Casilla de carcel
        x=5;
        y=671;
        cca = new CCarcel("Carcel",x,y);
        t.getCasillas().add(cca);
        
        //Casillas Segunda FIla
        x=19;
        y=568;
        vx=-57;
        cx=58;
        cy=-9;
        cp = new CPropiedad(x+cx,y+cy,"Plaza San Carlos",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cs=new CServicios(-1,"Compañia de Electricidad",x,y);
        t.getCasillas().add(cs);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Status",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Virginia",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cf = new CFerrocarril(-1,"Pennsylvania",x,y);
        t.getCasillas().add(cf);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Plaza St. James",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cc = new CCarta("Arca","Arca Comunal 2",x,y);
        t.getCasillas().add(cc);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Tennessee",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Nueva York",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        
        //Casilla Libre
        x=34;
        y=34;
        cl = new CLibre("Puesto Libre",x,y);
        t.getCasillas().add(cl);
        
        //Fila 3
        x=110;
        y=19;
        vx=57;
        cx=-10;
        cy=58;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Kentucky",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cc = new CCarta("Fortuna","Fortuna 2",x,y);
        t.getCasillas().add(cc);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Indiana",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Illinois",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cf = new CFerrocarril(-1,"B. & O.",x,y);
        t.getCasillas().add(cf);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Atlantico",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Ventnor",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        cs=new CServicios(-1,"Compañia de Agua",x,y);
        t.getCasillas().add(cs);
        x+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Jardines Marvin",x,y);
        t.getCasillas().add(cp);
        x+=vx;
        
        //Casilla ve a la Carcel
        x=644;
        y=34;
        cv = new CVeACarcel("Ve a la Carcel",x,y);
        t.getCasillas().add(cv);
        
        //Casillas fila 4
        x=657;
        y=109;
        vx=57;
        cx=-42;
        cy=-9;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Pacifico",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Carolina del Norte",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cc = new CCarta("Arca","Arca Comunal 3",x,y);
        t.getCasillas().add(cc);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Avenida Pennsylvania",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        cf = new CFerrocarril(-1,"Via Rapida",x,y);
        t.getCasillas().add(cf);
        y+=vx;
        cc = new CCarta("Fortuna","Fortuna 3",x,y);
        t.getCasillas().add(cc);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"Plaza Park",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        ci = new CImpuesto(200,"Luxury Tax",x,y);
        t.getCasillas().add(ci);
        y+=vx;
        cp = new CPropiedad(x+cx,y+cy,"El Muelle",x,y);
        t.getCasillas().add(cp);
        y+=vx;
        
    }
}
