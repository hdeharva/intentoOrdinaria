package org.vaadin.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class API {
    private static final String urlPrefix = "http://localhost:8080/%s/%s";
    public String getUserporNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "users/porNombre", nombre);
        //Si tiene espacios no funciona, cambiar el json
        System.out.println("Url: " + fullUrl);
        // %20
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
    public String getUserPorCorreo(String correo) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "users/porCorreo",correo);
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(fullUrl))
                    .GET()
                    .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();
    }
    public String getUsers() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "users","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();
    }
}