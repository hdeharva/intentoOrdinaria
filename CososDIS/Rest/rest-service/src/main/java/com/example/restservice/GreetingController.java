package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController //RestControler marca esta clase como un controlador donde cada metodo devuelve
//un objeto de dominio en lugar de una vista.
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    //GetMapping asegura que las solicitudes HTTP GET a /greeting se asignen al método greeting()
    //RequestParam vincula el valor del nombre del parametro de la cadena de consulta en el parametro
    //de nombre del metodo greeting, si no hay, se usa el default World
    //La implementación del cuerpo del metodo crea y devuelve un nuevo objeto Greeting con id
    //en funcion del siguiente valor del contador y formatea el nombre dado mediante el uso de la
    //plantilla de saludo

    //Gracias a RESTful, los datos del objeto se escribirán directamente en la respuesta HTTP como JSON
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/users")
    public ArrayList<User> users()
    {
        JsonReader reader = new JsonReader();
        ArrayList<User> userList = reader.readJsonFile("./src/main/resources/users.json");
        return userList;
    }

    @GetMapping("/users/porNombre/{name}")//ResponseEntity se encarga de representar la respuesta del HTTP,
    //junto a su codigo de estatus, headers y body
    public ResponseEntity<User> getByName(@PathVariable String name)
    {
        DataHandling dataHandling = new DataHandling();
        User foundUser = dataHandling.getUserInfo(name);
        System.out.println("Found user: " + foundUser.getName());
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("/users/porCorreo/{email}")//ResponseEntity se encarga de representar la respuesta del HTTP,
    //junto a su codigo de estatus, headers y body
    public ResponseEntity<User> getByEmail(@PathVariable String email)
    {
        DataHandling dataHandling = new DataHandling();
        User foundUser = dataHandling.getUserInfoByEmail(email);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    //PostMapping es para añadir objetos al JSON
    @PostMapping(path = "users",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User newUser)
    {
        DataHandling dataHandling = new DataHandling();
        ArrayList<User> user = dataHandling.addUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    //Put es para modificar un elemento ya existente
    @PutMapping(path = "{name}",
            consumes  = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity <ArrayList<User>> modificar(
            @PathVariable("name") String name,
            @RequestBody User newUser)
    {
        DataHandling dataHandling = new DataHandling();           //Me creo un objeto de tipo datahandling para llamar a la funcion modificarElementoFichero() mas tarde
        ArrayList<User> userList = dataHandling.editUser(newUser ,name);
        return new ResponseEntity<>(userList, HttpStatus.CREATED);        //Devuelvo el response entity
    }


    //Para borrar elementos
    @DeleteMapping(path = "{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name) {
        try {
            JsonReader reader = new JsonReader();
            reader.DeleteObjectJson(name, "./src/main/resources/users.json");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
