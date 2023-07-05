package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.googlecode.gentyref.TypeToken;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class PokemonService implements Serializable{
    public String leePokemonPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.getPokemonPorNombre(nombre);
    }

    public String leePokemonPorTipo(String tipo) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.getPokemonPorTipo(tipo);
    }

    public ArrayList<Pokemon> leePokemons() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getPokemons();
        Gson gson = new Gson();
        ArrayList<Pokemon> lista = gson.fromJson(resultsAPI, new TypeToken<ArrayList<Pokemon>>(){}.getType());
        return lista;
    }

}
