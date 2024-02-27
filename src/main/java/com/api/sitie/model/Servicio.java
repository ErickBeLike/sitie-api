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
    private Integer precio_servicio;
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

    public Integer getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(Integer precio_servicio) {
        this.precio_servicio = precio_servicio;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
}
