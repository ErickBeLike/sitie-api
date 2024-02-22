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
    @JoinColumn(name = "id_responsable")
    private Empleado id_responsable;
    @Column(name = "fecha_venta")
    private LocalDateTime fecha_venta;
    private float precio;

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

    public Empleado getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(Empleado id_responsable) {
        this.id_responsable = id_responsable;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
