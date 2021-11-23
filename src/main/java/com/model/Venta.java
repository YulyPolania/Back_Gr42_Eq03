package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(value = "ventas")
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Venta  {


	@Id
	private Long codigoVenta;
	private Long usuarioVenta;
	private Long clienteVenta;
	private Double ivaVenta;
	private Double totalIva;
	private Double totalVenta;
	private Long sedeIdVenta;
	private Date fechaVenta;

	
}