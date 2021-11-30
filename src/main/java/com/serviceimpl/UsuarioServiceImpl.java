package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.GenericImpl;
import com.model.Permiso;
import com.model.Role;
import com.model.Usuario;
import com.repository.UsuarioRepository;
import com.service.PermisoService;
import com.service.RoleService;
import com.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends GenericImpl<Usuario, Long> implements UsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected CrudRepository<Usuario, Long> getDao() {
		return usuarioRepository;
	}

	@Autowired
	private PermisoService permisoService;

	@Autowired
	private RoleService roleService;

	// ---------extraer listado de roles por usuario-------------------
	public List<String> getRoles(Long id) {
		List<Permiso> permisos = new ArrayList<Permiso>();
		List<String> roles = new ArrayList<String>();
		for (Permiso registro : permisoService.getAll()) {
			if (registro.getCedulaUsuario().equals(id)) {
				permisos.add(registro);
				for (Role rol : roleService.getAll()) {
					if (rol.getIdRol().equals(registro.getIdRol())) {
						roles.add(rol.getName());
					}
				}
			}
		}
		return roles;
	}
	// -------------fin extraer listado de permisos por usuario-------------------

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		if (user == null) {
			logger.error("No existe el usuario: " + username);
			throw new UsernameNotFoundException("No existe el usuario: " + username);
		}
		List<GrantedAuthority> authorities = getRoles(user.getCedulaUsuario())
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public Usuario findByUserName(String username) {
		Usuario user = usuarioRepository.findByUsername(username);
		return user;
	}
}
