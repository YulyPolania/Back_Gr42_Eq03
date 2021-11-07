package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Producto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, Long> {
  
}