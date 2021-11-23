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

import com.model.Venta;
import com.service.VentaService;




@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private VentaService ventaService;

	@GetMapping(value = "/all")
	public List<Venta> getAll() {
		return ventaService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Venta find(@PathVariable Long id) {
		return ventaService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Venta> save(@RequestBody Venta venta) {
		Venta obj = ventaService.save(venta);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Venta> delete(@PathVariable Long id) {
		Venta venta = ventaService.get(id);
		if (venta != null) {
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
}
	
	
	
