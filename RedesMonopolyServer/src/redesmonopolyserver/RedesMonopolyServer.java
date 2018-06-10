package redesmonopolyserver;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import redesmonopolyserver.Comunicacion.Cliente;
import redesmonopolyserver.Comunicacion.Servidor;

/**
 *
 * @author kamgm
 */
public class RedesMonopolyServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Servidor 1 Cliente 2");
        int respuesta=sc.nextInt();
        if (respuesta==1){
            try {
                Servidor s = new Servidor();
            } catch (IOException ex) {
                Logger.getLogger(RedesMonopolyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            
        }
    }
    
}
