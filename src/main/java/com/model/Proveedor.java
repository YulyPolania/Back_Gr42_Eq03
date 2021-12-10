package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if (!isValid("^\\d{1,15}$", this.nitProveedor.toString(), 15)) {
			return "El nit del proveedor sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^[a-zÀ-ÿA-Z\\s]{4,40}$", this.ciudadProveedor, 40)) {
			return "Nombre de ciudad inválido, sólo se permiten entre 4 y 40 caracteres";
		} else if (!isValid("^[a-zA-ZÀ-ÿ0-9!@#$%^&.*\\s]{8,100}$", this.direccionProveedor, 100)) {
			return "La dirección debe contener entre 8 y 100 caracteres máximo";
		} else if (!isValid("^[a-zA-Z0-9\\s_-]{8,40}$", this.telefonoProveedor, 40)) {
			return "El teléfono debe contener entre 8 y 40 caracteres.";
		} else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{3,40}$", this.nombreProveedor, 40)) {
			return "Nombre inválido, sólo se permiten entre 3 y 40 caracteres";
		}
		return null;
	}
}
