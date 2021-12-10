package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Producto;
import com.model.Proveedor;
import com.service.ProductoService;
import com.service.ProveedorService;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProveedorService proveedorService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_USER')")
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Producto> productoes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			productoes = productoService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productoes != null) {
			return new ResponseEntity<>(productoes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_USER')")
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (producto == null) {
			response.put("mensaje",
					"El producto con el código: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Producto producto) {
		Producto product = null;
		Map<String, Object> response = new HashMap<>();

		if (producto.validate() != null) {
			response.put("error", producto.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Proveedor supplier = proveedorService.get(producto.getNitProveedor());
		if (supplier == null) {
			response.put("error", "El proveedor con Nit: " + producto.getNitProveedor() + " no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (productoService.get(producto.getCodigoProducto()) != null) {
			response.put("error",
					"El producto con el código: " + producto.getNitProveedor() + " ya existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			product = productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con éxito!");
		response.put("producto", product);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PostMapping(value = "/import")
	public ResponseEntity<?> importCsv(@RequestBody List<Producto> productos) {
		Map<String, Object> response = new HashMap<>();
		List<String> mensajes = new ArrayList<String>();
		for (Producto i : productos) {
			String x = save(i).getBody().toString();
			mensajes.add(x);
		}
		response.put("mensaje", mensajes);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto updateProduct = null;
		Map<String, Object> response = new HashMap<>();
		try {
			Producto currentProduct = productoService.get(id);
			if (currentProduct == null) {
				response.put("error",
						"El producto con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			if (producto.validate() != null) {
				response.put("error", producto.validate());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			currentProduct.setNombreProducto(producto.getNombreProducto());
			currentProduct.setIvaCompra(producto.getIvaCompra());
			currentProduct.setPrecioCompra(producto.getPrecioCompra());
			currentProduct.setPrecioVenta(producto.getPrecioVenta());
			currentProduct.setNitProveedor(producto.getNitProveedor());
			updateProduct = productoService.save(currentProduct);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la actualización en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido actualizado con éxito!");
		response.put("producto", updateProduct);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERADMIN')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.get(id);
			if (producto == null) {
				response.put("error", "El producto con el código: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			productoService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito!");
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
