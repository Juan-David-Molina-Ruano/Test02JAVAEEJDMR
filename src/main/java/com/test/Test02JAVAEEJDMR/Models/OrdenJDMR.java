package com.test.Test02JAVAEEJDMR.Models;

import java.sql.Date;

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
