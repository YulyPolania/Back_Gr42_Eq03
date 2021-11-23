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

import com.model.DetalleVenta;
import com.service.DetalleVentaService;




@RestController
@RequestMapping("/detalleventas")
@CrossOrigin("*")
public class DetalleVentaController {

	@Autowired
	private DetalleVentaService detalleVentaService;

	@GetMapping(value = "/all")
	public List<DetalleVenta> getAll() {
		return detalleVentaService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public DetalleVenta find(@PathVariable Long id) {
		return detalleVentaService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<DetalleVenta> save(@RequestBody DetalleVenta detalleVenta) {
		DetalleVenta obj = detalleVentaService.save(detalleVenta);
		return new ResponseEntity<DetalleVenta>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<DetalleVenta> delete(@PathVariable Long id) {
		DetalleVenta detalleVenta = detalleVentaService.get(id);
		if (detalleVenta != null) {
			return new ResponseEntity<DetalleVenta>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<DetalleVenta>(detalleVenta, HttpStatus.OK);
	}
}
	
	
	
