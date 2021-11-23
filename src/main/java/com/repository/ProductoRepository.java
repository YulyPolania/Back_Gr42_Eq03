package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Producto;


public interface ProductoRepository extends MongoRepository<Producto, Long> {

}
