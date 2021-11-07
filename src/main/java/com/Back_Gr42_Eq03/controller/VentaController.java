package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Venta;
import com.Back_Gr42_Eq03.service.api.VentaServiceApi;

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
@RequestMapping("/api/ventas/v1")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private VentaServiceApi ventaServiceApi;

	@GetMapping(value = "/all")
	public List<Venta> getAll() {
		return ventaServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Venta find(@PathVariable Long id) {
		return ventaServiceApi.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Venta> save(@RequestBody Venta venta) {
		Venta obj = ventaServiceApi.save(venta);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Venta> delete(@PathVariable Long id) {
		Venta venta = ventaServiceApi.get(id);
		if (venta != null) {
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
}