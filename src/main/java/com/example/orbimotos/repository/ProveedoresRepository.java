package com.example.orbimotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orbimotos.model.entity.Proveedores;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Long> {
    
    // Método auxiliar para validar duplicados por NIT en las reglas de negocio
    boolean existsByNit(String nit);
}