package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, Long> {
  
}