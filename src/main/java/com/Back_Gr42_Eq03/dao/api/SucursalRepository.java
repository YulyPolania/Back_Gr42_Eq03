package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Sucursal;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SucursalRepository extends MongoRepository<Sucursal, Long> {
  
}