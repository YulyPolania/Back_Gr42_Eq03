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

import com.model.Cliente;
import com.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Cliente> clientees = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clientees = clienteService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (clientees != null) {
			return new ResponseEntity<>(clientees, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("mensaje", "El cliente con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		Cliente customer = null;
		Map<String, Object> response = new HashMap<>();
		if (cliente.validate() != null) {
			response.put("mensaje", cliente.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			customer = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", customer);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.get(id);
			if (cliente == null) {
				response.put("mensaje", "El cliente con la cédula: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
