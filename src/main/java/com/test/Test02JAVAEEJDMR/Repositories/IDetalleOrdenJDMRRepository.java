package com.test.Test02JAVAEEJDMR.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Test02JAVAEEJDMR.Models.DetalleOrdenJDMR;

public interface IDetalleOrdenJDMRRepository extends JpaRepository<DetalleOrdenJDMR, Integer>{
    Page<DetalleOrdenJDMR> findByOrderByOrdenJDMRDesc(Pageable pageable);
}
