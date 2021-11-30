package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Sede;

public interface SedeRepository extends MongoRepository<Sede, Integer> {

}
