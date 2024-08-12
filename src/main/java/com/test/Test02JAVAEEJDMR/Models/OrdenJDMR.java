package com.test.Test02JAVAEEJDMR.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Ordenes")
public class OrdenJDMR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fecha de orden
    @NotNull(message = "La fecha de la orden es requerida")
    private Date fechaOrdenJDMR;
    
    @OneToMany(mappedBy = "ordenJDMR", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenJDMR> detalles;


    public List<DetalleOrdenJDMR> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenJDMR> detalles) {
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaOrdenJDMR() {
        return fechaOrdenJDMR;
    }

    public void setFechaOrdenJDMR(Date fechaOrdenJDMR) {
        this.fechaOrdenJDMR = fechaOrdenJDMR;
    }




    
    
    

}
