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

import com.model.Permiso;
import com.service.PermisoService;




@RestController
@RequestMapping("/permisos")
@CrossOrigin("*")
public class PermisoController {

	
	
	@Autowired 
	private PermisoService permisoService;

	@GetMapping(value = "/all")
	public List<Permiso> getAll() {
		return permisoService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Permiso find(@PathVariable Integer id) {
		return permisoService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Permiso> save(@RequestBody Permiso permiso) {
		Permiso obj = permisoService.save(permiso);
		return new ResponseEntity<Permiso>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Permiso> delete(@PathVariable Integer id) {
		Permiso permiso = permisoService.get(id);
		if (permiso != null) {
			return new ResponseEntity<Permiso>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}
}
	
	
	
