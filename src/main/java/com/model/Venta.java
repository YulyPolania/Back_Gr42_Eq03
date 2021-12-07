package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document(value = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

	@Id
	private Long codigoVenta;
	private Long cedulaUsuario;
	private Long cedulaCliente;
	private Double ivaVenta;
	private Double totalIva;
	private Double totalVenta;
	private Long idSede;
	private Date fechaVenta;
	private List<DetalleVenta> detallesVenta;

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
		if (!isValid("^\\d{1,15}$", this.codigoVenta.toString(), 15)) {
			return "El código de la venta sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,15}$", this.cedulaUsuario.toString(), 15)) {
			return "La cédula del usuario sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,15}$", this.cedulaCliente.toString(), 15)) {
			return "El cédula del cliente sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,15}$", this.idSede.toString(), 15)) {
			return "El código de la sede sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.ivaVenta.toString(), 12)) {
			return "El iva de la venta sólo puede ser un decimal (.) o entero de máximo 12 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.totalIva.toString(), 12)) {
			return "El total de iva de la venta sólo puede ser un decimal (.) o entero de máximo 12 caracteres y 4 decimales";
		} else if (!isValid("^\\d{0,15}(\\.\\d{0,4}){0,1}$", this.totalVenta.toString(), 12)) {
			return "El total de la venta sólo puede ser un decimal (.) o entero de máximo 12 caracteres y 4 decimales";
		}
		return null;
	}
}