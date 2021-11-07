package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Venta;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VentaRepository extends MongoRepository<Venta, Long> {
  
}