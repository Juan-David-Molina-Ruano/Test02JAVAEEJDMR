package com.test.Test02JAVAEEJDMR.Services.Implements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.Test02JAVAEEJDMR.Models.OrdenJDMR;
import com.test.Test02JAVAEEJDMR.Repositories.IOrdenJDMRRepository;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IOrdenJDMRService;

@Service
public class OrdenJDMRService implements IOrdenJDMRService{

    @Autowired
    private IOrdenJDMRRepository ordenJDMRRepository;

    @Override
    public Page<OrdenJDMR> buscarTodosPaginados(Pageable pageable) {
        return ordenJDMRRepository.findAll(pageable);
    }

    @Override
    public List<OrdenJDMR> obtenerTodos() {
        return ordenJDMRRepository.findAll();
    }

    @Override
    public Optional<OrdenJDMR> buscarPorId(Long id) {
        return ordenJDMRRepository.findById(id.intValue());
    }

    @Override
    public OrdenJDMR crearOEditar(OrdenJDMR ordenJDMR) {
        return ordenJDMRRepository.save(ordenJDMR);
    }

    @Override
    public void eliminarPorId(Long id) {
        ordenJDMRRepository.deleteById(id.intValue());
    }
}
