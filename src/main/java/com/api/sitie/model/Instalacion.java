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
    @JoinColumn(name = "id_responsable")
    private Empleado id_responsable;
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

    public Empleado getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(Empleado id_responsable) {
        this.id_responsable = id_responsable;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }
}
