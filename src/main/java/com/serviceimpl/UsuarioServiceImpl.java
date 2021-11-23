package com.serviceimpl;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Usuario;
import com.repository.UsuarioRepository;
import com.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends GenericImpl<Usuario, Long> implements UsuarioService, UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected CrudRepository<Usuario, Long> getDao() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		if (user==null) {
			throw  new UsernameNotFoundException("Usuario no valido");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
	}

}
