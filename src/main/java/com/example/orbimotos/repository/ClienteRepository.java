package com.example.orbimotos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orbimotos.model.entity.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, long id);

    Optional<Clientes> findByNombreIgnoreCase(String nombre);
}
