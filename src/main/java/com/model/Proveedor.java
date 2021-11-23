package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Proveedor {
	@Id
	private Long nitProveedor;
	private String ciudadProveedor;
	private String direccionProveedor;
	private String nombreProveedor;
	private String telefonoProveedor;
	
}
