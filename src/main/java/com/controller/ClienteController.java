package com.controller;

import java.util.List;

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

import com.model.Cliente;
import com.service.ClienteService;




@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
    
	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/all")
	public List<Cliente> getAll() {
		return clienteService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Cliente find(@PathVariable Long id) {
		return clienteService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente obj = clienteService.save(cliente);
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		Cliente cliente = clienteService.get(id);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
}
	
	
	
