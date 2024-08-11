package com.test.Test02JAVAEEJDMR.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.Test02JAVAEEJDMR.Models.OrdenJDMR;

public interface IOrdenJDMRService {
    Page<OrdenJDMR> buscarTodosPaginados(Pageable pageable);

    List<OrdenJDMR> obtenerTodos();

    Optional<OrdenJDMR> buscarPorId(Long id);

    OrdenJDMR crearOEditar(OrdenJDMR ordenJDMR);

    void eliminarPorId(Long id);
}
