package com.example.orbimotos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orbimotos.exception.RecursoNoEncontradoException;
import com.example.orbimotos.exception.ReglaNegocioException;
import com.example.orbimotos.model.entity.Clientes;
import com.example.orbimotos.repository.ClientesRepository;

@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public List<Clientes> listar() {
        return clientesRepository.findAll();
    }

    public Clientes buscarPorId(long id) {
        return clientesRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id: " + id));
    }

    /**
     * REGLA DE NEGOCIO 1: Validar que el nombre del cliente no esté vacío.
     */
    public Clientes guardar(Clientes cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new ReglaNegocioException("El nombre del cliente es obligatorio.");
        }
        return clientesRepository.save(cliente);
    }

    /**
     * REGLA DE NEGOCIO 2: Validar actualización del cliente.
     */
    public Clientes actualizar(long id, Clientes clienteDetalles) {
        Clientes clienteExistente = buscarPorId(id);

        if (clienteDetalles.getNombre() == null || clienteDetalles.getNombre().trim().isEmpty()) {
            throw new ReglaNegocioException("El nombre del cliente no puede estar vacío.");
        }

        clienteExistente.setNombre(clienteDetalles.getNombre());
        if (clienteDetalles.getTelefono() != null) {
            clienteExistente.setTelefono(clienteDetalles.getTelefono());
        }
        if (clienteDetalles.getEmail() != null) {
            clienteExistente.setEmail(clienteDetalles.getEmail());
        }

        return clientesRepository.save(clienteExistente);
    }

    public void eliminar(long id) {
        Clientes cliente = buscarPorId(id);
        clientesRepository.delete(cliente);
    }
}