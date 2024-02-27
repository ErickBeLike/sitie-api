package com.api.sitie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instalacion")
public class Instalacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_instalacion")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta id_venta;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado id_empleado;
    @Column(name = "fecha_instalacion")
    private String fecha_instalacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }

    public Empleado getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Empleado id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }
}
