package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	@Id
	private Long codigoProducto;
	private Double ivacompra;
	private String nombreProducto;
	private Double precioCompra;
	private Double precioVenta;
	private Long proveedoresNit;
	

}
