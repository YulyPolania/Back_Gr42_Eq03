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

import com.model.Sede;
import com.service.SedeService;




@RestController
@RequestMapping("/sedes")
@CrossOrigin("*")
public class SedeController {

	
	
	@Autowired 
	private SedeService sedeService;

	@GetMapping(value = "/all")
	public List<Sede> getAll() {
		return sedeService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Sede find(@PathVariable Integer id) {
		return sedeService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Sede> save(@RequestBody Sede sede) {
		Sede obj = sedeService.save(sede);
		return new ResponseEntity<Sede>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Sede> delete(@PathVariable Integer id) {
		Sede sede = sedeService.get(id);
		if (sede != null) {
			return new ResponseEntity<Sede>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Sede>(sede, HttpStatus.OK);
	}
}
	
	
	
