package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Permiso;

public interface PermisoRepository extends MongoRepository<Permiso, Integer> {
	public List<Permiso> findByCedulaUsuario(Long idUsuario);
}
