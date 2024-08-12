package com.test.Test02JAVAEEJDMR.Models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "DetalleOrdenes")
public class DetalleOrdenJDMR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad es requerida")
    private int cantidadJDMR;

    @NotNull(message = "El precio es requerido")
    private BigDecimal precioJDMR;

    @ManyToOne(cascade = CascadeType.ALL) // Agrega cascade aquí
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoJDMR productoJDMR;

    @ManyToOne(cascade = CascadeType.ALL) // Agrega cascade aquí
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenJDMR ordenJDMR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadJDMR() {
        return cantidadJDMR;
    }

    public void setCantidadJDMR(int cantidadJDMR) {
        this.cantidadJDMR = cantidadJDMR;
    }

    public BigDecimal getPrecioJDMR() {
        return precioJDMR;
    }

    public void setPrecioJDMR(BigDecimal precioJDMR) {
        this.precioJDMR = precioJDMR;
    }

    public ProductoJDMR getProductoJDMR() {
        return productoJDMR;
    }

    public void setProductoJDMR(ProductoJDMR productoJDMR) {
        this.productoJDMR = productoJDMR;
    }

    public OrdenJDMR getOrdenJDMR() {
        return ordenJDMR;
    }

    public void setOrdenJDMR(OrdenJDMR ordenJDMR) {
        this.ordenJDMR = ordenJDMR;
    }

}
