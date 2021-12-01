package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.model.Permiso;

public interface PermisoRepository extends MongoRepository<Permiso, Integer> {

	@Query
	List<Permiso> findByCedulaUsuario(Long CedulaUsuario);
}
