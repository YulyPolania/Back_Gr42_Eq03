package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Venta;


public interface VentaRepository extends MongoRepository<Venta, Long> {

}
