package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Cliente;
import com.Back_Gr42_Eq03.service.api.ClienteServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/clientes/v1")
@CrossOrigin("*")
public class ClienteController {

	@Autowired
	private ClienteServiceApi clienteServiceApi;

	@GetMapping(value = "/all")
	public List<Cliente> getAll() {
		return clienteServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Cliente find(@PathVariable Long id) {
		return clienteServiceApi.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente obj = clienteServiceApi.save(cliente);
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		Cliente cliente = clienteServiceApi.get(id);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
}