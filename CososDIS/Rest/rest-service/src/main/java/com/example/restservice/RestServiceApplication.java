package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //es una anotación de conveniencia que agrega lo siguiente:
/*
	@Configuration: etiqueta la clase como fuente de definiciones de bean para el contexto de la aplicacion

	@EnableAUtoConfiguration: le dice a Spring Boot que comiencea agregar beans
	según la configuración de classpath, otros beans y varias configuraciones de
	propiedades. Por ejemplo, si spring-webmvc está en el class_path, esta anotación
	marca la aplicación como una aplicación web y activa comportamientos clave, como
	configurar un DispatcherServlet.

	@ComponentScan: le dice a Spring que busque otros componentes, configuraciones
	y servicios en el paquete com/example, permitiéndole encontrar los controladores.
 */
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
