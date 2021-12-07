package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {
	@Id
	private Integer idRol;
	private String name;

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
		if (!isValid("^\\d{1,8}$", this.idRol.toString(), 8)) {
			return "El código del rol sólo puede contener números, máximo 8 caracteres sin espacios o puntos";
		} else if (!isValid("^[a-zA-Z]{4,10}$", this.name, 10)) {
			return "Nombre del rol inválido, sólo se permiten entre 4 y 10 caracteres sin espacios";
		}
		return null;
	}
}
