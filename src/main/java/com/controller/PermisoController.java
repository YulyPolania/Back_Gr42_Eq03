package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Permiso;
import com.service.PermisoService;

@RestController
@RequestMapping("/permisos")
@CrossOrigin("*")
public class PermisoController {

	@Autowired
	private PermisoService permisoService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Permiso> permisoes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permisoes = permisoService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (permisoes != null) {
			return new ResponseEntity<>(permisoes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permiso = permisoService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (permiso == null) {
			response.put("mensaje", "El permiso con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Permiso permiso) {
		Permiso pass = null;
		Map<String, Object> response = new HashMap<>();
		if (permiso.validate() != null) {
			response.put("mensaje", permiso.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			pass = permisoService.save(permiso);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido creado con éxito!");
		response.put("permiso", pass);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permiso = permisoService.get(id);
			if (permiso == null) {
				response.put("mensaje", "El permiso con el código: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			permisoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido eliminado con éxito!");
		response.put("permiso", permiso);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
