package dis.ufv.pokemonRestAPI.pokemonAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LectorJson {
/*
    public ArrayList<Pokemon> leeFicheroJson(String fichero) {

        // Forma 1: pasando t0do el rato la ruta del fichero
        try{
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            ArrayList<Pokemon> listaPokemon = new Gson().fromJson(reader, new TypeToken<ArrayList<Pokemon>>() {}.getType());
            reader.close();
            return listaPokemon;
        } catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        } */


        // Forma 2: pasando directamente el fichero, funciona mejor con Docker
        // solo cambia la primera línea, y las llamadas se hacen sin la ruta

    public ArrayList<Pokemon> leeFicheroJson(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("pokemonConId.json")));
            ArrayList<Pokemon> listaPokemon = new Gson().fromJson(reader, new TypeToken<ArrayList<Pokemon>>() {}.getType());
            reader.close();
            return listaPokemon;
        } catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }

    }
}
