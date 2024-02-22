package com.api.sitie.controller;

import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Empleado;
import com.api.sitie.repository.EmpleadoRepository; // Asegúrate de tener el repositorio correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable(value = "id") Long idEmpleado) throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id ::" + idEmpleado));
        return ResponseEntity.ok().body(empleado);
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable(value = "id") Long idEmpleado, @RequestBody Empleado datosEmpleado)
            throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + idEmpleado));

        empleado.setNombre_empleado(datosEmpleado.getNombre_empleado());
        empleado.setRol_empleado(datosEmpleado.getRol_empleado());
        empleado.setTelefono_empleado(datosEmpleado.getTelefono_empleado());
        empleado.setCorreo_empleado(datosEmpleado.getCorreo_empleado());

        final Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public Map<String, Boolean> eliminarEmpleado(@PathVariable(value = "id") Long idEmpleado) throws ResourceNotFoundException {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + idEmpleado));

        empleadoRepository.delete(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
