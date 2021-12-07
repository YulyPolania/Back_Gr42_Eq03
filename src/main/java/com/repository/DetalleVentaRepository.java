package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.DetalleVenta;

public interface DetalleVentaRepository extends MongoRepository<DetalleVenta, Long> {

}
