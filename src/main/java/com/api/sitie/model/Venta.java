package com.api.sitie.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_venta")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio id_servicio;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado id_empleado;
    @Column(name = "fecha_venta")
    private LocalDateTime fecha_venta;
    @Column(name = "precio_servicio")
    private Integer precio_servicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Servicio getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Servicio id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Empleado getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Empleado id_empleado) {
        this.id_empleado = id_empleado;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Integer getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(Integer precio_servicio) {
        this.precio_servicio = precio_servicio;
    }
}
