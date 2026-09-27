package com.example.orbimotos.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repuestos")
@Schema(description = "Entidad que guarda los datos de los repuestos")

public class Repuestos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "identificador unico de repuesto", example = "001")
    private long id;

    @Schema(description = "Nombre de repuesto (el nombre debe empezar con mayuscula)", example = "Filtro de aceite")
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
