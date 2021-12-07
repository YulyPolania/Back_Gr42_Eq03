package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Usuario;
import com.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Usuario> users = null;
		Map<String, Object> response = new HashMap<>();
		try {
			users = usuarioService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_SUPERADMIN')")
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Usuario user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = usuarioService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			response.put("mensaje",
					"El usuario con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_SUPERADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Usuario usuario) {
		Usuario user = null;
		Map<String, Object> response = new HashMap<>();
		if (usuario.validate() != null) {
			response.put("mensaje", usuario.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			user = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_SUPERADMIN')")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario updateUser = null;
		Map<String, Object> response = new HashMap<>();

		try {
			Usuario currentUser = usuarioService.get(id);
			if (currentUser == null) {
				response.put("mensaje",
						"El usuario con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if (!currentUser.getPassword().equals(usuario.getPassword())) {
				if (usuario.password() != null) {
					response.put("mensaje", usuario.password());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				currentUser.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			}
			if (usuario.validate() != null) {
				response.put("mensaje", usuario.validate());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			currentUser.setEmailUsuario(usuario.getEmailUsuario());
			currentUser.setNombreUsuario(usuario.getNombreUsuario());
			currentUser.setUsername(usuario.getUsername());
			updateUser = usuarioService.save(currentUser);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la actualización en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario", updateUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_SUPERADMIN')")
	@Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_SUPERADMIN",})
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Usuario user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = usuarioService.get(id);
			if (user == null) {
				response.put("mensaje", "El usuario con la cédula: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado con éxito!");
		response.put("usuario", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
