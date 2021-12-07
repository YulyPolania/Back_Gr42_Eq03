package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "permisos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permiso {

	@Id
	private Integer idPermiso;
	private Integer idRol;
	private Integer idSede;
	private Long cedulaUsuario;

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
		if (!isValid("^\\d{1,8}$", this.idPermiso.toString(), 8)) {
			return "El código del permiso sólo puede contener números, máximo 8 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,8}$", this.idRol.toString(), 8)) {
			return "El código del rol sólo puede contener números, máximo 8 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,8}$", this.idSede.toString(), 8)) {
			return "El código de la sede sólo puede contener números, máximo 8 caracteres sin espacios o puntos";
		} else if (!isValid("^\\d{1,15}$", this.cedulaUsuario.toString(), 15)) {
			return "La cédula del usuario sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		}
		return null;
	}
}
