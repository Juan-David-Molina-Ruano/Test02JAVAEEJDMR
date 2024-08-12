package com.test.Test02JAVAEEJDMR.Models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Productos")
public class ProductoJDMR {
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nombre
    @NotBlank(message = "El nombre es requerido")
    private String nombreJDMR;

    @OneToMany(mappedBy = "productoJDMR", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenJDMR> detalles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreJDMR() {
        return nombreJDMR;
    }

    public void setNombreJDMR(String nombreJDMR) {
        this.nombreJDMR = nombreJDMR;
    }

    public List<DetalleOrdenJDMR> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenJDMR> detalles) {
        this.detalles = detalles;
    }

}
