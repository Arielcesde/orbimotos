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
		//[REGLA DE NEGOCIO 1:] Validar que el id del repuesto sea mayor que cero
		if (id <= 0) {
			throw new IllegalArgumentException("El id del repuesto debe ser mayor que cero");
		}

		//[REGLA DE NEGOCIO 2:] Validar que el repuesto exista en la base de datos

		return repuestosRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe un repuesto con el id " + id));
	}

	

	public Repuestos guardar(Repuestos repuesto) {
		validarRepuesto(repuesto);
		repuesto.setNombre(repuesto.getNombre().trim());
		return repuestosRepository.save(repuesto);
	}

	public Repuestos actualizar(long id, Repuestos repuesto) {
		 // Al llamar a buscarPorId se evalúan implícitamente:
        // -> [REGLA 1: Validación del ID]
        // -> [REGLA 2: Existencia del Registro]
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
		//[REGLA DE NEGOCIO 3:] Validar que el nombre del repuesto no esté vacío
		if (repuesto == null || repuesto.getNombre() == null || repuesto.getNombre().isBlank()) {
			throw new IllegalArgumentException("El nombre del repuesto es obligatorio");
		}

		String nombre = repuesto.getNombre().trim();

		//[REGLA DE NEGOCIO 4:] Validar que el nombre del repuesto comience con mayúscula
		if (!Character.isUpperCase(nombre.charAt(0))) {
			throw new IllegalArgumentException("El nombre del repuesto debe comenzar con mayúscula");
		}
	}
}


