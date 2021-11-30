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

import com.model.Proveedor;
import com.service.ProveedorService;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin("*")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Proveedor> proveedores = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedores = proveedorService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (proveedores != null) {
			return new ResponseEntity<>(proveedores, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Proveedor proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor = proveedorService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (proveedor == null) {
			response.put("mensaje", "El proveedor con el nit: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Proveedor proveedor) {
		Proveedor supplier = null;
		Map<String, Object> response = new HashMap<>();
		if (proveedor.validate() != null) {
			response.put("mensaje", proveedor.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			supplier = proveedorService.save(proveedor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El proveedor ha sido creado con éxito!");
		response.put("proveedor", supplier);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Proveedor proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor = proveedorService.get(id);
			if (proveedor == null) {
				response.put("mensaje", "El proveedor con el nit: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			proveedorService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El proveedor ha sido eliminado con éxito!");
		response.put("proveedor", proveedor);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
