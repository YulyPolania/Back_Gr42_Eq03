package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private Long nitProveedor;

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
		if (!isValid("^\\d{1,15}$", this.codigoProducto.toString(), 15)) {
			return "El código del producto sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{8,40}$", this.nombreProducto, 40)) {
			return "Nombre inválido, sólo se permiten entre 8 y 40 caracteres";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.ivacompra.toString(), 20)) {
			return "El iva de compra sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.precioCompra.toString(), 20)) {
			return "El precio de compraa sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.precioVenta.toString(), 20)) {
			return "El precio de venta sólo puede ser un decimal (.) o entero de máximo 15 caracteres y 4 decimales";
		} else if (!isValid("^\\d{1,15}$", this.nitProveedor.toString(), 15)) {
			return "El nit del proveedor sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		}
		return null;
	}
}
