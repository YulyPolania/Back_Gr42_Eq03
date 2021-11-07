package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Proveedor;
import com.Back_Gr42_Eq03.service.api.ProveedorServiceApi;

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
@RequestMapping("/api/proveedors/v1")
@CrossOrigin("*")
public class ProveedorController {

	@Autowired
	private ProveedorServiceApi proveedorServiceApi;

	@GetMapping(value = "/all")
	public List<Proveedor> getAll() {
		return proveedorServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Proveedor find(@PathVariable Long id) {
		return proveedorServiceApi.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor) {
		Proveedor obj = proveedorServiceApi.save(proveedor);
		return new ResponseEntity<Proveedor>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Proveedor> delete(@PathVariable Long id) {
		Proveedor proveedor = proveedorServiceApi.get(id);
		if (proveedor != null) {
			return new ResponseEntity<Proveedor>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}
}