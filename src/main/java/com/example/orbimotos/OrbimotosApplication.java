package com.example.orbimotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class OrbimotosApplication {

    public static void main(String[] args) {
        // Carga las variables del archivo .env al entorno de Java
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(OrbimotosApplication.class, args);
    }
}