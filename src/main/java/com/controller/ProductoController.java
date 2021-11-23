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

import com.model.Producto;
import com.service.ProductoService;




@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

	
	
	@Autowired 
	private ProductoService productoService;

	@GetMapping(value = "/all")
	public List<Producto> getAll() {
		return productoService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Producto find(@PathVariable Long id) {
		return productoService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Producto> save(@RequestBody Producto producto) {
		Producto obj = productoService.save(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Producto> delete(@PathVariable Long id) {
		Producto producto = productoService.get(id);
		if (producto != null) {
			return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
}
	
	
	
