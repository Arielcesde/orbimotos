package com.example.orbimotos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orbimotos.model.entity.Proveedores;
import com.example.orbimotos.service.ProveedoresService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public ResponseEntity<List<Proveedores>> listar() {
        return ResponseEntity.ok(proveedoresService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(proveedoresService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Proveedores> guardar(@RequestBody Proveedores proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedoresService.guardar(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedores> actualizar(
            @PathVariable long id,
            @RequestBody Proveedores proveedor) {
        return ResponseEntity.ok(proveedoresService.actualizar(id, proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        proveedoresService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}