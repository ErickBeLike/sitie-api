package com.api.sitie.controller;

import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Cliente;
import com.api.sitie.repository.ClienteRepository; // Asegúrate de tener el repositorio correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable(value = "id") Long idCliente) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el id ::" + idCliente));
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/clientes")
    public Cliente agregarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable(value = "id") Long idCliente, @RequestBody Cliente datosCliente)
            throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el id :: " + idCliente));

        cliente.setNombre_cliente(datosCliente.getNombre_cliente());
        cliente.setTelefono_cliente(datosCliente.getTelefono_cliente());
        cliente.setCorreo_cliente(datosCliente.getCorreo_cliente());
        cliente.setDireccion(datosCliente.getDireccion());

        final Cliente clienteActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/clientes/{id}")
    public Map<String, Boolean> eliminarCliente(@PathVariable(value = "id") Long idCliente) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el id :: " + idCliente));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
