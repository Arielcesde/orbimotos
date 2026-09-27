package com.example.orbimotos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orbimotos.exception.RecursoNoEncontradoException;
import com.example.orbimotos.exception.ReglaNegocioException;
import com.example.orbimotos.model.entity.Proveedores;
import com.example.orbimotos.repository.ProveedoresRepository;

@Service
public class ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresService(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    public List<Proveedores> listar() {
        return proveedoresRepository.findAll();
    }

    public Proveedores buscarPorId(long id) {
        return proveedoresRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proveedor no encontrado con id: " + id));
    }

    /**
     * REGLA DE NEGOCIO 1: Validar que el NIT no sea nulo ni esté duplicado en la base de datos.
     */
    public Proveedores guardar(Proveedores proveedor) {
        if (proveedor.getNit() == null || proveedor.getNit().trim().isEmpty()) {
            throw new ReglaNegocioException("El NIT del proveedor es obligatorio.");
        }
        
        if (proveedoresRepository.existsByNit(proveedor.getNit())) {
            throw new ReglaNegocioException("Ya existe un proveedor registrado con el NIT: " + proveedor.getNit());
        }

        return proveedoresRepository.save(proveedor);
    }

    /**
     * REGLA DE NEGOCIO 2: Validar que el nombre del proveedor no esté vacío al actualizar.
     */
    public Proveedores actualizar(long id, Proveedores proveedorDetalles) {
        Proveedores proveedorExistente = buscarPorId(id);

        if (proveedorDetalles.getNombre() == null || proveedorDetalles.getNombre().trim().isEmpty()) {
            throw new ReglaNegocioException("El nombre del proveedor no puede estar vacío.");
        }

        proveedorExistente.setNombre(proveedorDetalles.getNombre());
        if (proveedorDetalles.getTelefono() != null) {
            proveedorExistente.setTelefono(proveedorDetalles.getTelefono());
        }
        if (proveedorDetalles.getDireccion() != null) {
            proveedorExistente.setDireccion(proveedorDetalles.getDireccion());
        }

        return proveedoresRepository.save(proveedorExistente);
    }

    public void eliminar(long id) {
        Proveedores proveedor = buscarPorId(id);
        proveedoresRepository.delete(proveedor);
    }
}