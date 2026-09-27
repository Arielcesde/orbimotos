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

import com.example.orbimotos.model.entity.Clientes;
import com.example.orbimotos.service.ClientesService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

	private final ClientesService clientesService;

	public ClientesController(ClientesService clientesService) {
		this.clientesService = clientesService;
	}

	@GetMapping
	public ResponseEntity<List<Clientes>> listar() {
		return ResponseEntity.ok(clientesService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Clientes> buscarPorId(@PathVariable long id) {
		return ResponseEntity.ok(clientesService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Clientes> guardar(@RequestBody Clientes cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.guardar(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Clientes> actualizar(
			@PathVariable long id,
			@RequestBody Clientes cliente) {
		return ResponseEntity.ok(clientesService.actualizar(id, cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable long id) {
		clientesService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
