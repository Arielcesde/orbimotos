package com.example.orbimotos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orbimotos.model.entity.Repuestos;
import com.example.orbimotos.repository.RepuestosRepository;

@Service
public class RepuestosService {

	private final RepuestosRepository repuestosRepository;

	public RepuestosService(RepuestosRepository repuestosRepository) {
		this.repuestosRepository = repuestosRepository;
	}

	public List<Repuestos> listar() {
		return repuestosRepository.findAll();
	}

	public Repuestos buscarPorId(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El id del repuesto debe ser mayor que cero");
		}

		return repuestosRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe un repuesto con el id " + id));
	}

	public Repuestos guardar(Repuestos repuesto) {
		validarRepuesto(repuesto);
		repuesto.setNombre(repuesto.getNombre().trim());
		return repuestosRepository.save(repuesto);
	}

	public Repuestos actualizar(long id, Repuestos repuesto) {
		buscarPorId(id);
		validarRepuesto(repuesto);

		repuesto.setId(id);
		repuesto.setNombre(repuesto.getNombre().trim());
		return repuestosRepository.save(repuesto);
	}

	public void eliminar(long id) {
		buscarPorId(id);
		repuestosRepository.deleteById(id);
	}

	private void validarRepuesto(Repuestos repuesto) {
		if (repuesto == null || repuesto.getNombre() == null || repuesto.getNombre().isBlank()) {
			throw new IllegalArgumentException("El nombre del repuesto es obligatorio");
		}

		String nombre = repuesto.getNombre().trim();
		if (!Character.isUpperCase(nombre.charAt(0))) {
			throw new IllegalArgumentException("El nombre del repuesto debe comenzar con mayúscula");
		}
	}
}
