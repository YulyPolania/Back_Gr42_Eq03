package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Producto;
import com.Back_Gr42_Eq03.service.api.ProductoServiceApi;

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
@RequestMapping("/api/productos/v1")
@CrossOrigin("*")
public class ProductoController {

	@Autowired
	private ProductoServiceApi productoServiceApi;

	@GetMapping(value = "/all")
	public List<Producto> getAll() {
		return productoServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Producto find(@PathVariable Long id) {
		return productoServiceApi.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Producto> save(@RequestBody Producto producto) {
		Producto obj = productoServiceApi.save(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Producto> delete(@PathVariable Long id) {
		Producto producto = productoServiceApi.get(id);
		if (producto != null) {
			return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
}