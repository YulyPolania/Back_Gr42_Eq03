package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<?> getAll() {
		List<DetalleVenta> detalleVentas = null;
		Map<String, Object> response = new HashMap<>();
		try {
			detalleVentas = detalleVentaService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (detalleVentas != null) {
			return new ResponseEntity<>(detalleVentas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		DetalleVenta detalleVenta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			detalleVenta = detalleVentaService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (detalleVenta == null) {
			response.put("mensaje", "El usuario con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DetalleVenta>(detalleVenta, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody DetalleVenta detalleVenta) {
		DetalleVenta detailSale = null;
		Map<String, Object> response = new HashMap<>();
		if (detalleVenta.validate() != null) {
			response.put("error", detalleVenta.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			detailSale = detalleVentaService.save(detalleVenta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El detalle de venta ha sido creado con éxito!");
		response.put("detalleVenta", detailSale);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		DetalleVenta detalleVenta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			detalleVenta = detalleVentaService.get(id);
			if (detalleVenta == null) {
				response.put("mensaje", "El detalle de venta con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			detalleVentaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El detalle de venta ha sido eliminado con éxito!");
		response.put("detalleVenta", detalleVenta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
