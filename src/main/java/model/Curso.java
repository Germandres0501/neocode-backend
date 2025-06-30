package com.neocode.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cursos")  // ✅ MongoDB usa @Document
public class Curso {

    @Id
    private String id;  // ✅ MongoDB usa String como ID

    private String nombre;
    private String descripcion;

    // Constructor vacío (requerido por Spring)
    public Curso() {}

    // Constructor con parámetros
    public Curso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
