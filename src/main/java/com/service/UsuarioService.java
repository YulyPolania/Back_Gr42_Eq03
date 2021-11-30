package com.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.commons.GenericService;
import com.model.Usuario;

public interface UsuarioService extends GenericService<Usuario, Long> {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public Usuario findByUserName(String username);

}
