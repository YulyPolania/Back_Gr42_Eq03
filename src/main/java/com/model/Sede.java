package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sedes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sede {

	@Id
	private Integer idSede;
	private String ciudadSede;
	private String direccionSede;
	private String nombreSede;
	private String telefonoSede;

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
		if (!isValid("^\\d{1,8}$", this.idSede.toString(), 8)) {
			return "El código de la sede sólo puede contener números, máximo 8 caracteres sin espacios o puntos";
		} else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{8,40}$", this.ciudadSede, 40)) {
			return "Nombre de ciudad inválido, sólo se permiten entre 8 y 40 caracteres";
		} else if (!isValid("^[a-zA-Z0-9!@#$%^&.*\\s]{8,100}$", this.direccionSede, 100)) {
			return "La dirección debe contener entre 8 y 100 caracteres máximo";
		} else if (!isValid("^[a-zA-Z0-9_-\\s]{4,40}$", this.telefonoSede, 40)) {
			return "El teléfono debe contener entre 4 y 40 caracteres.";
		}
		return null;
	}
}
