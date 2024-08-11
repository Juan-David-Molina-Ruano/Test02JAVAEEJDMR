package com.test.Test02JAVAEEJDMR.Services.Implements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;
import com.test.Test02JAVAEEJDMR.Repositories.IProductoJDMRRepository;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IProductoJDMRService;

@Service
public class ProductoJDMRService implements IProductoJDMRService{

    @Autowired
    private IProductoJDMRRepository productoJDMRRepository;

    @Override
    public Page<ProductoJDMR> buscarTodosPaginados(Pageable pageable) {
        return productoJDMRRepository.findAll(pageable);
    }

    @Override
    public List<ProductoJDMR> obtenerTodos() {
        return productoJDMRRepository.findAll();
    }

    @Override
    public Optional<ProductoJDMR> buscarPorId(Long id) {
        return productoJDMRRepository.findById(id.intValue());
    }

    @Override
    public ProductoJDMR crearOEditar(ProductoJDMR productoJDMR) {
        return productoJDMRRepository.save(productoJDMR);
    }

    @Override
    public void eliminarPorId(Long id) {
        productoJDMRRepository.deleteById(id.intValue());
    }
}
