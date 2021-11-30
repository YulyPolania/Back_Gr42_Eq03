package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Role;

public interface RoleRepository extends MongoRepository<Role, Integer> {

}
