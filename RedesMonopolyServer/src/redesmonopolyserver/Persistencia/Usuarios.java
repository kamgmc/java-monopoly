
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
        //Si no funciona el de arriba usar el siguiente: 
        /* 
        try(FileWriter file = new FileWriter("/Persistencia/usuario_json.txt");
        */             
    }
    public static Usuarios obtenerUsuario(String usuarioBuscado) {
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
                    return new Usuarios(username,clave,nombre,apellido);
                }
            }
            /*
            String nombre = (String) jsonObject.get("Nombre");
            String apellido = (String) jsonObject.get("Apellido");
            String username = (String) jsonObject.get("Username");
            String clave = (String) jsonObject.get("Clave");
           // JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Username: " + username);
            System.out.println("Clave: " + clave);*/
           // System.out.println("\nCompany List:");
           // Iterator<String> iterator = companyList.iterator();
          //  while (iterator.hasNext()) {
           //     System.out.println(iterator.next());
           // }
 
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
