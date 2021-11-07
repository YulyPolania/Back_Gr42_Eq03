package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.UsuarioRepository;
import com.Back_Gr42_Eq03.model.Usuario;
import com.Back_Gr42_Eq03.service.api.UsuarioServiceApi;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioServiceApi {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected CrudRepository<Usuario, Long> getDao() {
		return usuarioRepository;
	}
}