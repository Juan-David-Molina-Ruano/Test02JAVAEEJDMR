package com.test.Test02JAVAEEJDMR.Services.Implements;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.test.Test02JAVAEEJDMR.Models.DetalleOrdenJDMR;
import com.test.Test02JAVAEEJDMR.Repositories.IDetalleOrdenJDMRRepository;
import com.test.Test02JAVAEEJDMR.Services.Interfaces.IDetalleOrdenJDMRService;

@Service
public class DetalleOrdenJDMRService implements IDetalleOrdenJDMRService{

     @Autowired
    private IDetalleOrdenJDMRRepository detalleOrdenJDMRRepository;

    @Override
    public Page<DetalleOrdenJDMR> buscarTodosPaginados(Pageable pageable) {
        return detalleOrdenJDMRRepository.findAll(pageable);
    }

    @Override
    public List<DetalleOrdenJDMR> obtenerTodos() {
        return detalleOrdenJDMRRepository.findAll();
    }

    @Override
    public Optional<DetalleOrdenJDMR> buscarPorId(Long id) {
        return detalleOrdenJDMRRepository.findById(id.intValue());
    }

    @Override
    public DetalleOrdenJDMR crearOEditar(DetalleOrdenJDMR detalleOrdenJDMR) {
        return detalleOrdenJDMRRepository.save(detalleOrdenJDMR);
    }

    @Override
    public void eliminarPorId(Long id) {
        detalleOrdenJDMRRepository.deleteById(id.intValue());
    }
}
