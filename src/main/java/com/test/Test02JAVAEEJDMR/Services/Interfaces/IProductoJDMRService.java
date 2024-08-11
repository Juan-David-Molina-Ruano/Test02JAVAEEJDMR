package com.test.Test02JAVAEEJDMR.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;

public interface IProductoJDMRService {
    Page<ProductoJDMR> buscarTodosPaginados(Pageable pageable);

    List<ProductoJDMR> obtenerTodos();

    Optional<ProductoJDMR> buscarPorId(Long id);

    ProductoJDMR crearOEditar(ProductoJDMR productoJDMR);

    void eliminarPorId(Long id);
}
