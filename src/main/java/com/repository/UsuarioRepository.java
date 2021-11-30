package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {
	@Query
	Usuario findByUsername(String username);

}
