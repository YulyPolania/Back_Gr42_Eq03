package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Proveedor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProveedorRepository extends MongoRepository<Proveedor, Long> {
  
}