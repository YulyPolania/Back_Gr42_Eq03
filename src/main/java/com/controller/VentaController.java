package com.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.DetalleVenta;
import com.model.Venta;
import com.service.SequenceGeneratorService;
import com.service.VentaService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private VentaService ventaService;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Venta> ventas = null;
		Map<String, Object> response = new HashMap<>();
		try {
			ventas = ventaService.getAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ventas != null) {
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Venta venta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			venta = ventaService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (venta == null) {
			response.put("mensaje", "La venta con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}

	@GetMapping(value = "/findDetails/{id}")
	public ResponseEntity<?> findDetails(@PathVariable Long id) {
		Venta venta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			venta = ventaService.get(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (venta == null) {
			response.put("mensaje", "La venta con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}

	@GetMapping(value = "/byCustomer")
	public ResponseEntity<?> salesByCustomer() {
		List<String[]> resul = new ArrayList<String[]>();
		Map<String, Object> response = new HashMap<>();
		try {
			resul = ventaService.getVentasByCliente();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (resul == null) {
			response.put("mensaje", "No hay registros");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String[]>>(resul, HttpStatus.OK);
	}

	@GetMapping(value = "/bySede")
	public ResponseEntity<?> salesBySede() {
		List<String[]> resul = new ArrayList<String[]>();
		Map<String, Object> response = new HashMap<>();
		try {
			resul = ventaService.getVentasBySede();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (resul == null) {
			response.put("mensaje", "No hay registros");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String[]>>(resul, HttpStatus.OK);
	}



	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Venta venta) {

		Venta sale = null;
		Map<String, Object> response = new HashMap<>();
		Long codigo = sequenceGeneratorService.generateLongSequence(ventaService.getAllCodigoVenta());
		venta.setCodigoVenta(codigo);

		if(venta.getDetallesVenta().isEmpty()){
			response.put("error", "Sin detalles de venta");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Long cdv = 1L;
		for(DetalleVenta i:venta.getDetallesVenta()){
			i.setCodigoDetalleVenta(Long.parseLong(codigo+""+cdv));
			cdv++;
		}
		if (venta.validate() != null) {
			response.put("error", venta.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			venta.setFechaVenta(new Date());
			System.out.println("\n\n\n"+venta+"\n\n\n");
			sale = ventaService.save(venta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La venta ha sido creada con éxito!");
		response.put("venta", sale);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Venta venta, @PathVariable Long id) {
		Venta updateVenta = null;
		Map<String, Object> response = new HashMap<>();	

		try {
			Venta currentVenta = ventaService.get(id);
			if (currentVenta == null) {
				response.put("mensaje",
				"La venta con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			Long cdv = 1L;
			for(DetalleVenta i:venta.getDetallesVenta()){
				i.setCodigoDetalleVenta(Long.parseLong(id+""+cdv));
				cdv++;
			}
			
			currentVenta.setDetallesVenta(venta.getDetallesVenta());
			currentVenta.setTotalVenta(venta.getTotalVenta());
			currentVenta.setIvaVenta(venta.getIvaVenta());
			currentVenta.setTotalIva(venta.getTotalIva());
			
			System.out.println("_______186_______\n\n\n\n"+currentVenta+"\n\n\n\n______186____");
			updateVenta = ventaService.save(currentVenta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la actualización en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La venta ha sido actualizado con éxito!");
		response.put("venta", updateVenta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Venta venta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			venta = ventaService.get(id);
			if (venta == null) {
				response.put("mensaje", "La venta con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			ventaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la eliminación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La venta ha sido eliminada con éxito!");
		response.put("venta", venta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
