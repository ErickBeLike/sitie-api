package com.api.sitie.controller;


import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Servicio;
import com.api.sitie.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public List<Servicio> obtenerTodosLosServicios() {
        return servicioRepository.findAll();
    }

    @GetMapping("/servicios/{id}")
    public ResponseEntity<Servicio> buscarServicioId(@PathVariable(value = "id") Long idServicio) throws ResourceNotFoundException {
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el id ::" + idServicio));
        return ResponseEntity.ok().body(servicio);
    }

    @PostMapping("/servicios")
    public Servicio agregarServicio(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @PutMapping("/servicios/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable(value = "id") Long idServicio, @RequestBody Servicio datosServicio)
            throws ResourceNotFoundException {
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el id :: " + idServicio));
        servicio.setNombre_servicio(datosServicio.getNombre_servicio());
        servicio.setPrecio(datosServicio.getPrecio());
        servicio.setDescricion(datosServicio.getDescricion());

        final Servicio servicioActualizado = servicioRepository.save(servicio);
        return ResponseEntity.ok(servicioActualizado);
    }

    @DeleteMapping("/servicios/{id}")
    public Map<String, Boolean> eliminarServicio(@PathVariable(value = "id") Long idServicio) throws ResourceNotFoundException {
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el id :: " + idServicio));

        servicioRepository.delete(servicio);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}