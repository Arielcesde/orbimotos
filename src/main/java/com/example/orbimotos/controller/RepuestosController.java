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

import com.example.orbimotos.model.entity.Repuestos;
import com.example.orbimotos.service.RepuestosService;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestosController {

    private final RepuestosService repuestosService;

    public RepuestosController(RepuestosService repuestosService) {
        this.repuestosService = repuestosService;
    }

    @GetMapping
    public ResponseEntity<List<Repuestos>> listar() {
        return ResponseEntity.ok(repuestosService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuestos> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(repuestosService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Repuestos> guardar(@RequestBody Repuestos repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestosService.guardar(repuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuestos> actualizar(
            @PathVariable long id,
            @RequestBody Repuestos repuesto) {
        return ResponseEntity.ok(repuestosService.actualizar(id, repuesto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        repuestosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

