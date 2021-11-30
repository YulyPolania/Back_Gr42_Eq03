package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private Long codigoProducto;
	private Long codigoVenta;

	private Boolean isValid(String regex, String value, Integer lenght) {
		if (value == null) {
			return false;
		}
		if (lenght != null && value.length() > lenght) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public String validate() {
		if (!isValid("^\\d{1,15}$", this.codigoDetalleVenta.toString(), 15)) {
			return "El código del detalle de venta sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,8}$", this.cantidadProducto.toString(), 8)) {
			return "La cantidad de producto sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.valorTotal.toString(), 20)) {
			return "El valor total del detalle de venta sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.valorVenta.toString(), 20)) {
			return "El valor del detalle de venta sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.valorIva.toString(), 20)) {
			return "El valor del iva del detalle de venta sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{1,15}$", this.codigoProducto.toString(), 15)) {
			return "El código del producto sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,15}$", this.codigoVenta.toString(), 15)) {
			return "El código de la venta sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		}
		return null;
	}
}
