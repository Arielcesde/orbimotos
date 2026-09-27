package com.example.orbimotos.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
@Schema(description = "Entidad donde de regitran y guardaon los datos de los proveedores")

public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "identificador unico de proveedor", example = "Nit")
    private long id;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id; 
    }
}
