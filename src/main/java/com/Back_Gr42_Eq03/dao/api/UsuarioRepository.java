package com.Back_Gr42_Eq03.dao.api;

import com.Back_Gr42_Eq03.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {
}