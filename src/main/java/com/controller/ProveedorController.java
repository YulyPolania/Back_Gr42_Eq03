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

import com.model.Proveedor;
import com.service.ProveedorService;




@RestController
@RequestMapping("/proveedores")
@CrossOrigin("*")
public class ProveedorController {

	
	
	@Autowired 
	private ProveedorService proveedorService;

	@GetMapping(value = "/all")
	public List<Proveedor> getAll() {
		return proveedorService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Proveedor find(@PathVariable Long id) {
		return proveedorService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor) {
		Proveedor obj = proveedorService.save(proveedor);
		return new ResponseEntity<Proveedor>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Proveedor> delete(@PathVariable Long id) {
		Proveedor proveedor = proveedorService.get(id);
		if (proveedor != null) {
			return new ResponseEntity<Proveedor>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}
}
	
	
	
