package com.test.Test02JAVAEEJDMR.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Productos")
public class ProductoJDMR {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nombre
    @NotBlank(message = "El nombre es requerido")
    private String nombreJDMR;

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


    
}
