package com.api.sitie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_servicio")
    private Long id;
    @Column(name = "nombre_servicio")
    private String nombre_servicio;
    @Column(name = "precio_servicio")
    private Float precio;
    @Column(name = "descricion")
    private String descricion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
}
