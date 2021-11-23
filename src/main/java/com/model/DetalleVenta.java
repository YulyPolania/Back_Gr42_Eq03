package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "detalle_ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DetalleVenta {
	
	@Id
	private Long codigoDetalleVenta;
	private Integer cantidadProducto;
	private Double valorTotal;
	private Double valorVenta;
	private Double valorIva;
	private Long ProductosCodigo;
	private Long ventasCodigo;

}
