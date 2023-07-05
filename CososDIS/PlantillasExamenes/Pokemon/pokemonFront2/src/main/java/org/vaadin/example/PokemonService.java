package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class PokemonService implements Serializable {

    public Pokemon leerPokemonPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getPokemonPorNombre(nombre);
        Gson gson = new Gson();
        Pokemon pokemon = gson.fromJson(resultsAPI, new TypeToken<Pokemon>(){}.getType());
        return pokemon;
    }
    public ArrayList<Pokemon> leerPokemonPorTipo(String tipo) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getPokemonPorTipo(tipo);
        Gson gson = new Gson();
        ArrayList<Pokemon> listaPokemons = gson.fromJson(resultsAPI, new TypeToken<ArrayList<Pokemon>>(){}.getType());
        return listaPokemons;
    }
    public ArrayList<Pokemon> leerPokemons() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getPokemons();
        Gson gson = new Gson();
        ArrayList<Pokemon> listaPokemons = gson.fromJson(resultsAPI, new TypeToken<ArrayList<Pokemon>>(){}.getType());
        return listaPokemons;
    }

}
