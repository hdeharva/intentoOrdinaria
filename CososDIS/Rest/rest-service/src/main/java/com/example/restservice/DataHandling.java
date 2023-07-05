package com.example.restservice;

import java.util.ArrayList;
import java.util.List;


//Clase que se encarga de devolver un User si lo encuentra en nuestro JSON
public class DataHandling {
    User getUserInfo (String name){
        User foundUser = null;
        JsonReader reader = new JsonReader();

        ArrayList<User> usersList = reader.readJsonFile("./src/main/resources/users.json");
        for (User user : usersList){
            if (user.getName().equalsIgnoreCase(name)){
                foundUser = user;
            }
        }
        System.out.println("User encontrado:" + foundUser.getName());
        return foundUser;
    }

    User getUserInfoByEmail (String name){
        User foundUser = null;
        JsonReader reader = new JsonReader();

        ArrayList<User> usersList = reader.readJsonFile("./src/main/resources/users.json");
        for (User user : usersList){
            if (user.getEmail().equalsIgnoreCase(name)){
                foundUser = user;
            }
        }

        return foundUser;
    }


    //Añade el usuario al ArrayList y al JSON
    ArrayList<User> addUser (User newUser)
    {
        JsonReader reader = new JsonReader();
        ArrayList<User> userList = reader.readJsonFile("./src/main/resources/users.json");
        userList.add(newUser);  //Añade un objeto nuevo obtenido mediante parametros al ArrayList
        //Actualizamos el JSON
        reader.writeJsonFile("./src/main/resources/users.json", userList);
        return userList; //Devolvemos el ArrayList actualizado
    }

    //Editamos el objeto en la lista que coincida con el nombre que le pasemos
    ArrayList<User> editUser (User newUser , String name){

        JsonReader reader = new JsonReader();                   //me declaro un objeto del tipo json
        ArrayList<User> usersList =  reader.readJsonFile("./src/main/resources/users.json");    //declar una lista de tipo classdato1 que busque al usuario


        for(User user : usersList){                        //me declaro un foreach para recorrer la lista con todos los elementos en busca del codigo de geometria que quiero
            if (user.getName().equalsIgnoreCase(newUser.getName())){         //si lo encuentro , modifico los sets
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                user.setRoles(newUser.getRoles());
                user.setAdmin(newUser.isAdmin());


            }
        }
        //Reescribimos en el json con los cambios
        reader.writeJsonFile("./src/main/resources/users.json", usersList);            //Escribo en el fichero JSON


        return usersList;                //devuelvo la lista
    }
}
