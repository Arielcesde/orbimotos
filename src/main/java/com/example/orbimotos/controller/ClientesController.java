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
public class ClienteController {

	private final ClientesService clienteServie;

	public ClienteController(ClientesService clienteServie) {
		this.clienteServie = clienteServie;
	}

	@GetMapping
	public ResponseEntity<List<Clientes>> listar() {
		return ResponseEntity.ok(clienteServie.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Clientes> buscarPorId(@PathVariable long id) {
		return ResponseEntity.ok(clienteServie.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Clientes> guardar(@RequestBody Clientes cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteServie.guardar(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Clientes> actualizar(
			@PathVariable long id,
			@RequestBody Clientes cliente) {
		return ResponseEntity.ok(clienteServie.actualizar(id, cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable long id) {
		clienteServie.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
