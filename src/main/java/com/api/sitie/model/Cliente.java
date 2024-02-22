package com.api.sitie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Long id;
    @Column(name = "nombre_cliente")
    private String nombre_cliente;
    @Column(name = "telefono_cliente")
    private String telefono_cliente;
    @Column(name = "correo_cliente")
    private String correo_cliente;
    @Column(name = "direccion")
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
