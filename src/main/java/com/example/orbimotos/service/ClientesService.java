package com.example.orbimotos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orbimotos.model.entity.Clientes;
import com.example.orbimotos.repository.ClientesRepository;

@Service
public class ClientesService {

	private final ClientesRepository clienteRepository;

	public ClientesService(ClientesRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Clientes> listar() {
		return clienteRepository.findAll();
	}

	public Clientes buscarPorId(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El id del cliente debe ser mayor que cero");
		}

		return clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe un cliente con el id " + id));
	}

	public Clientes guardar(Clientes cliente) {
		validarCliente(cliente);

		if (clienteRepository.existsByNombreIgnoreCase(cliente.getNombre().trim())) {
			throw new IllegalArgumentException("Ya existe un cliente con ese nombre");
		}

		cliente.setNombre(cliente.getNombre().trim());
		return clienteRepository.save(cliente);
	}

	public Clientes actualizar(long id, Clientes cliente) {
		buscarPorId(id);
		validarCliente(cliente);

		if (clienteRepository.existsByNombreIgnoreCaseAndIdNot(cliente.getNombre().trim(), id)) {
			throw new IllegalArgumentException("Ya existe otro cliente con ese nombre");
		}

		cliente.setId(id);
		cliente.setNombre(cliente.getNombre().trim());
		return clienteRepository.save(cliente);
	}

	public void eliminar(long id) {
		buscarPorId(id);
		clienteRepository.deleteById(id);
	}

	private void validarCliente(Clientes cliente) {
		if (cliente == null || cliente.getNombre() == null || cliente.getNombre().isBlank()) {
			throw new IllegalArgumentException("El nombre del cliente es obligatorio");
		}

		String nombre = cliente.getNombre().trim();
		if (!Character.isUpperCase(nombre.charAt(0))) {
			throw new IllegalArgumentException("El nombre del cliente debe comenzar con mayúscula");
		}
	}
}

