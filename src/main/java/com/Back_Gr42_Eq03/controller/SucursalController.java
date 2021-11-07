package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Sucursal;
import com.Back_Gr42_Eq03.service.api.SucursalServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sucursals/v1")
@CrossOrigin("*")
public class SucursalController {

	@Autowired
	private SucursalServiceApi sucursalServiceApi;

	@GetMapping(value = "/all")
	public List<Sucursal> getAll() {
		return sucursalServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Sucursal find(@PathVariable Long id) {
		return sucursalServiceApi.get(id);
	}

	// @PostMapping(value = "/save")
	// public ResponseEntity<Sucursal> save(@RequestBody Sucursal sucursal) {
	// 	Sucursal obj = sucursalServiceApi.save(sucursal);
	// 	return new ResponseEntity<Sucursal>(obj, HttpStatus.OK);
	// }

	// @GetMapping(value = "/delete/{id}")
	// public ResponseEntity<Sucursal> delete(@PathVariable Long id) {
	// 	Sucursal sucursal = sucursalServiceApi.get(id);
	// 	if (sucursal != null) {
	// 		return new ResponseEntity<Sucursal>(HttpStatus.NO_CONTENT);
	// 	}
	// 	return new ResponseEntity<Sucursal>(sucursal, HttpStatus.OK);
	// }
}