package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, Long>{

}
