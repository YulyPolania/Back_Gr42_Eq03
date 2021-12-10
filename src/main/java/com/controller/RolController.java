package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Role;
import com.service.RoleService;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolController {

	@Autowired
	private RoleService roleService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Role> roles = null;
		Map<String, Object> response = new HashMap<>();
		try {
			roles = roleService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (roles != null) {
			return new ResponseEntity<>(roles, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Role rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rol = roleService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (rol == null) {
			response.put("error", "El rol con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(rol, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Role role) {
		Role rol = null;
		Map<String, Object> response = new HashMap<>();
		if (role.validate() != null) {
			response.put("error", role.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			rol = roleService.save(role);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El rol ha sido creado con éxito!");
		response.put("role", rol);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Role rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rol = roleService.get(id);
			if (rol == null) {
				response.put("error", "El rol con el código: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			roleService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El rol ha sido eliminado con éxito!");
		response.put("role", rol);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
