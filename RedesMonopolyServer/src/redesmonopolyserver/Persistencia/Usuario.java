
package redesmonopolyserver.Persistencia;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Usuario implements Serializable{

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    
    public Usuario(String username, String password, String nombre, String apellido) {
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
    
    public void guardarUsuario(){
        JSONObject obj = new JSONObject();
        JSONArray usuarios = obtenerTodosLosUsuarios();
        //JSONArray usuarios = new JSONArray();
        obj.put("Username", username);
        obj.put("Clave",password);
        obj.put("Nombre",nombre);
        obj.put("Apellido",apellido);
        usuarios.add(obj);
        try{
            FileWriter file = new FileWriter("src/redesmonopolyserver/Persistencia/usuario_json.json");
            file.write(usuarios.toJSONString());
            System.out.println("Succesfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
            file.flush();
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }           
    }
    
    public static Usuario obtenerUsuario(String usuarioBuscado) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray usuarios = obtenerTodosLosUsuarios();
            for(int i = 0; i<usuarios.size();i++){
                JSONObject obj = (JSONObject) usuarios.get(i);
                String nombre = obj.get("Nombre").toString();
                String apellido = obj.get("Apellido").toString();
                String username = obj.get("Username").toString();
                String clave = obj.get("Clave").toString();
                if(usuarioBuscado.equals(username)){
                    return new Usuario(username,clave,nombre,apellido);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static JSONArray obtenerTodosLosUsuarios(){
        JSONParser parser = new JSONParser();
        JSONArray usuarios = new JSONArray();
        try {
 
            usuarios = (JSONArray)parser.parse(new FileReader(
                    "src/redesmonopolyserver/Persistencia/usuario_json.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public String toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("Username", username);
        obj.put("Clave",password);
        obj.put("Nombre",nombre);
        obj.put("Apellido",apellido);
        return obj.toString();
    
    }
    
    public static Usuario fromJSON(String json){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(json);
            String nombre = obj.get("Nombre").toString();
            String apellido = obj.get("Apellido").toString();
            String username = obj.get("Username").toString();
            String clave = obj.get("Clave").toString();
            Usuario u = new Usuario(username,clave,nombre,apellido);
            return u;
        } catch (ParseException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
}
