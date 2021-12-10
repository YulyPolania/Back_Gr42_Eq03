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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Permiso;
import com.service.PermisoService;
import com.service.RoleService;
import com.service.SedeService;
import com.service.SequenceGeneratorService;

@RestController
@RequestMapping("/permisos")
@CrossOrigin("*")
public class PermisoController {

	@Autowired
	private PermisoService permisoService;

	@Autowired
	private SedeService sedeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Permiso> permisos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permisos = permisoService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (permisos != null) {
			return new ResponseEntity<>(permisos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permiso = permisoService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (permiso == null) {
			response.put("error",
					"El permiso con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Permiso permiso) {
		Permiso pass = null;
		Map<String, Object> response = new HashMap<>();

		Boolean rol = roleService.isRole(permiso.getIdRol());
		Boolean sede = sedeService.isSede(permiso.getIdSede());

		if (!(rol && sede)) {
			String mensaje = rol ? "Id de sede no existe en la base de datos!" : "id de rol no existe en la base de datos!";
			response.put("error", mensaje);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (permisoService.isPermiso(permiso)) {
			response.put("error", "La combinación de rol y sede ya existe!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Integer id = sequenceGeneratorService.generateIntegerSequence(permisoService.getAllIdPermisos());
		permiso.setIdPermiso(id);
		if (permiso.validate() != null) {
			response.put("error", permiso.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			pass = permisoService.save(permiso);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido creado con éxito!");
		response.put("permiso", pass);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PostMapping(value = "/saveList")
	public ResponseEntity<?> saveList(@RequestBody List<Permiso> permisos){
		Map<String, Object> response = new HashMap<>();
		for(Permiso i:permisos){
			save(i);
		}
		response.put("mensaje", "Permisos creados con éxito!");
		response.put("permisos", permisos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Permiso permiso, @PathVariable Integer id) {
		Permiso updatePermiso = null;
		Map<String, Object> response = new HashMap<>();

		Boolean rol = roleService.isRole(permiso.getIdRol());
		Boolean sede = sedeService.isSede(permiso.getIdSede());

		if (!(rol && sede)) {
			String mensaje = rol ? "Id de sede no existe en la base de datos!" : "id de rol no existe en la base de datos!";
			response.put("error", mensaje);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (permisoService.isPermiso(permiso)) {
			response.put("error", "La combinación de rol y sede ya existe!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			Permiso currentPermiso = permisoService.get(id);
			if (currentPermiso == null) {
				response.put("error",
						"El permiso con el id: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			if (permiso.validate() != null) {
				response.put("error", permiso.validate());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			currentPermiso.setIdRol(permiso.getIdRol());
			currentPermiso.setIdSede(permiso.getIdSede());
			updatePermiso = permisoService.save(currentPermiso);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la actualización en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido actualizado con éxito!");
		response.put("permiso", updatePermiso);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
		try {
			permiso = permisoService.get(id);
			if (permiso == null) {
				response.put("error", "El permiso con el código: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			permisoService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido eliminado con éxito!");
		response.put("permiso", permiso);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@DeleteMapping(value = "/deleteByCedulaUsuario/{id}")
	public ResponseEntity<?> deleteByCedulaUsuario(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			permisoService.deleteByCedulaUsuario(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
