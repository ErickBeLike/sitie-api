package com.api.sitie.controller;

import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Cliente;
import com.api.sitie.model.Empleado;
import com.api.sitie.model.Servicio;
import com.api.sitie.model.Venta;
import com.api.sitie.repository.ClienteRepository;
import com.api.sitie.repository.EmpleadoRepository;
import com.api.sitie.repository.VentaRepository;
import com.api.sitie.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/ventas")
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity<Venta> buscarVentaPorId(@PathVariable(value = "id") Long idVenta) throws ResourceNotFoundException {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el id :: " + idVenta));
        return ResponseEntity.ok().body(venta);
    }

    @PostMapping("/ventas")
    public Venta agregarVenta(@RequestBody Map<String, Long> datosVenta) throws ResourceNotFoundException {
        // Validar que las IDs existan en las tablas correspondientes
        Cliente cliente = clienteRepository.findById(datosVenta.get("id_cliente"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el id :: " + datosVenta.get("id_cliente")));
        Servicio servicio = servicioRepository.findById(datosVenta.get("id_servicio"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el id :: " + datosVenta.get("id_servicio")));
        Empleado responsable = empleadoRepository.findById(datosVenta.get("id_responsable"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + datosVenta.get("id_responsable")));

        // Crear una nueva venta
        Venta nuevaVenta = new Venta();
        nuevaVenta.setId_cliente(cliente);
        nuevaVenta.setId_servicio(servicio);
        nuevaVenta.setId_responsable(responsable);
        nuevaVenta.setFecha_venta(LocalDateTime.now());
        nuevaVenta.setPrecio(servicio.getPrecio());

        // Guardar la venta
        return ventaRepository.save(nuevaVenta);
    }

    @PutMapping("/ventas/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable(value = "id") Long idVenta, @RequestBody Map<String, Long> datosVenta)
            throws ResourceNotFoundException {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el id :: " + idVenta));

        // Validar que las IDs existan en las tablas correspondientes
        Cliente cliente = clienteRepository.findById(datosVenta.get("id_cliente"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el id :: " + datosVenta.get("id_cliente")));
        Servicio servicio = servicioRepository.findById(datosVenta.get("id_servicio"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el id :: " + datosVenta.get("id_servicio")));
        Empleado responsable = empleadoRepository.findById(datosVenta.get("id_responsable"))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + datosVenta.get("id_responsable")));

        // Actualizar los campos de la venta según los datos proporcionados
        venta.setId_cliente(cliente);
        venta.setId_servicio(servicio);
        venta.setId_responsable(responsable);
        venta.setFecha_venta(LocalDateTime.now()); // Actualizar la fecha al momento de la modificación
        venta.setPrecio(servicio.getPrecio());

        // Guardar la venta actualizada
        final Venta ventaActualizada = ventaRepository.save(venta);
        return ResponseEntity.ok(ventaActualizada);
    }

    @DeleteMapping("/ventas/{id}")
    public Map<String, Boolean> eliminarVenta(@PathVariable(value = "id") Long idVenta) throws ResourceNotFoundException {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el id :: " + idVenta));

        // Elimina la venta
        ventaRepository.delete(venta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
