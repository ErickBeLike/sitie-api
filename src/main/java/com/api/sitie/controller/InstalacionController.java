package com.api.sitie.controller;

import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Empleado;
import com.api.sitie.model.Instalacion;
import com.api.sitie.model.Venta;
import com.api.sitie.repository.EmpleadoRepository;
import com.api.sitie.repository.InstalacionRepository;
import com.api.sitie.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InstalacionController {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/instalaciones")
    public List<Instalacion> obtenerTodasLasInstalaciones() {
        return instalacionRepository.findAll();
    }

    @GetMapping("/instalaciones/{id}")
    public ResponseEntity<Instalacion> buscarInstalacionPorId(@PathVariable(value = "id") Long idInstalacion) throws ResourceNotFoundException {
        Instalacion instalacion = instalacionRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una instalación para el id :: " + idInstalacion));
        return ResponseEntity.ok().body(instalacion);
    }

    @PostMapping("/instalaciones")
    public Instalacion agregarInstalacion(@RequestBody Map<String, Object> datosInstalacion) throws ResourceNotFoundException {
        // Validar que las IDs existan en las tablas correspondientes
        Venta venta = ventaRepository.findById(Long.parseLong(datosInstalacion.get("id_venta").toString()))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el id :: " + datosInstalacion.get("id_venta")));
        Empleado empleado = empleadoRepository.findById(Long.parseLong(datosInstalacion.get("id_responsable").toString()))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + datosInstalacion.get("id_responsable")));

        // Crear una nueva instalación
        Instalacion nuevaInstalacion = new Instalacion();
        nuevaInstalacion.setId_venta(venta);
        nuevaInstalacion.setId_empleado(empleado);
        nuevaInstalacion.setFecha_instalacion(datosInstalacion.get("fecha_instalacion").toString());

        // Guardar la instalación
        return instalacionRepository.save(nuevaInstalacion);
    }


    @PutMapping("/instalaciones/{id}")
    public ResponseEntity<Instalacion> actualizarInstalacion(@PathVariable(value = "id") Long idInstalacion, @RequestBody Map<String, Object> datosInstalacion)
            throws ResourceNotFoundException {
        Instalacion instalacion = instalacionRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una instalación para el id :: " + idInstalacion));

        // Validar que las IDs existan en las tablas correspondientes
        Venta venta = ventaRepository.findById(Long.parseLong(datosInstalacion.get("id_venta").toString()))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el id :: " + datosInstalacion.get("id_venta")));
        Empleado empleado = empleadoRepository.findById(Long.parseLong(datosInstalacion.get("id_responsable").toString()))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + datosInstalacion.get("id_responsable")));

        // Actualizar los campos de la instalación según los datos proporcionados
        instalacion.setId_venta(venta);
        instalacion.setId_empleado(empleado);
        instalacion.setFecha_instalacion(datosInstalacion.get("fecha_instalacion").toString());

        // Guardar la instalación actualizada
        final Instalacion instalacionActualizada = instalacionRepository.save(instalacion);
        return ResponseEntity.ok(instalacionActualizada);
    }


    @DeleteMapping("/instalaciones/{id}")
    public Map<String, Boolean> eliminarInstalacion(@PathVariable(value = "id") Long idInstalacion) throws ResourceNotFoundException {
        Instalacion instalacion = instalacionRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una instalación para el id :: " + idInstalacion));

        // Elimina la instalación
        instalacionRepository.delete(instalacion);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
