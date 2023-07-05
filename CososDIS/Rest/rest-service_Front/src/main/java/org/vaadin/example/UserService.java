package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
@Service
public class UserService implements Serializable{
    //Este servicio se encarga de comunicarse con la API
    public String leeUserporNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        //System.out.println("LeeUser con " + nombre);
        return api.getUserporNombre(nombre);
    }
    public String leeUserporCorreo(String correo) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.getUserPorCorreo(correo);
    }
    public ArrayList<User> leeUsers() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getUsers();
        Gson gson = new Gson();
        //Transforma de json a lista lo que obtengamos de los usuarios de la lista
        ArrayList<User> lista = gson.fromJson(resultsAPI,new
            TypeToken<ArrayList<User>>(){}.getType());
        return lista;     }
}
