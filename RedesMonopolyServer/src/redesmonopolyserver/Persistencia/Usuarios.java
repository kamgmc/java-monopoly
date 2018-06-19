
package redesmonopolyserver.Persistencia;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Usuarios {

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    
    public Usuarios(String username, String password, String nombre, String apellido) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
    }
 
        public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void guardarUsuario() throws IOException{
        JSONObject obj = new JSONObject();
		obj.put("Nombre", "Alexander");
                obj.put("Apellido","Garcia");
                obj.put("Username","Alexdgn213");
                obj.put("Clave","123456");
                FileWriter file = new FileWriter("/Persistencia/usuario_json.txt");
                try{
                    file.write(obj.toJSONString());
                    System.out.println("Succesfully Copied JSON Object to File...");
                    System.out.println("\nJSON Object: " + obj);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                finally{
                    file.flush();
                    file.close();
                }
                //Si no funciona el de arriba usar el siguiente: 
                /* 
                try(FileWriter file = new FileWriter("/Persistencia/usuario_json.txt");
                */
                
    }
    public void leerUsuario()throws IOException {
    JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(
                    "/Persistencia/usuario_json.txt"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String nombre = (String) jsonObject.get("Nombre");
            String apellido = (String) jsonObject.get("Apellido");
            String username = (String) jsonObject.get("Username");
            String clave = (String) jsonObject.get("Clave");
           // JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Username: " + username);
            System.out.println("Clave: " + clave);
           // System.out.println("\nCompany List:");
           // Iterator<String> iterator = companyList.iterator();
          //  while (iterator.hasNext()) {
           //     System.out.println(iterator.next());
           // }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public void leerUsuario(){
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader("f:\\test.json"));
            JSONObject jsonObject = (JSONObject)obj;
            System.out.println(jsonObject);
            String name = (String) jsonObject.get("name");
            System.out.println(name);
            long age = (Long) jsonObject.get("age");
            System.out.println(age);
            //loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());   
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }*/
    
    
}
