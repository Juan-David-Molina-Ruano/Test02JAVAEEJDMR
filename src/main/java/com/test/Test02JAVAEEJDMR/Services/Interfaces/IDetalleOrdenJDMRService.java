package com.test.Test02JAVAEEJDMR.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.Test02JAVAEEJDMR.Models.DetalleOrdenJDMR;

public interface IDetalleOrdenJDMRService {
    Page<DetalleOrdenJDMR> buscarTodosPaginados(Pageable pageable);

    List<DetalleOrdenJDMR> obtenerTodos();

    Optional<DetalleOrdenJDMR> buscarPorId(Long id);

    DetalleOrdenJDMR crearOEditar(DetalleOrdenJDMR detalleOrdenJDMR);

    void eliminarPorId(Long id);
}
