package com.example.restservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public ArrayList<User> readJsonFile(String fichero)
    {
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            // convierte el array JSON a un arraylist de users
            ArrayList<User> users = new Gson().fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            //users.forEach(System.out::println);// imprime los users
            reader.close();// close reader
            return users;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public boolean writeJsonFile(String fichero, ArrayList<User> users)
    {
        try{
            //lee el fichero que le pasamos y lo abre en escritura
            Writer writer = Files.newBufferedWriter(Paths.get((fichero)));
            //Convierte el arraylist de users a un JSON y lo escribe en el fichero
            writer.write(new Gson().toJson(users));
            writer.close(); //Cerrar lector
            return true; //Devuelve verdadero si ha leido algo
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false; //Devuelve falso si no ha leido nada
        }
    }

    public void DeleteObjectJson(String name, String fichero) {
        JsonReader reader = new JsonReader();
        ArrayList<User> Userlist = reader.readJsonFile(fichero);
        for (int i = 0; i < Userlist.size(); i++) {
            if (Userlist.get(i).getName().equalsIgnoreCase(name)){
                Userlist.remove(i);
                break;
            }
        }
        reader.writeJsonFile("./src/main/resources/users.json", Userlist);
        //Reescribirmos en el json la lista modificada
    }
}
