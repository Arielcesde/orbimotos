package com.example.orbimotos.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
@Schema(description = "Entidas que guarda los datos de los clientes")

public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "identificador unico de cliente", example = "001")
    private long id;

    @Schema(description = "Nombre de cliente (el nombre debe empezar con mayuscula)", example = "Mario Rios")
    private String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
