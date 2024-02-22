package com.api.sitie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado")
    private Long id;
    @Column(name = "nombre_empleado")
    private String nombre_empleado;
    @Column(name = "rol_empleado")
    private String rol_empleado;
    @Column(name = "telefono_empleado")
    private String telefono_empleado;
    @Column(name = "correo_empleado")
    private String correo_empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol_empleado() {
        return rol_empleado;
    }

    public void setRol_empleado(String rol_empleado) {
        this.rol_empleado = rol_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getCorreo_empleado() {
        return correo_empleado;
    }

    public void setCorreo_empleado(String correo_empleado) {
        this.correo_empleado = correo_empleado;
    }
}
