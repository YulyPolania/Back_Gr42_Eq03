package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	private Long cedulaUsuario;
	private String emailUsuario;
	private String nombreUsuario;
	private String password;
	private String username;

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
		if (!isValid("^\\d{1,15}$", this.cedulaUsuario.toString(), 15)) {
			return "La cédula del usuario sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
		} else if (!isValid("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9]{2,6}$", this.emailUsuario, 60)) {
			return "Correo electrónico inválido, se permiten hata 60 caracteres, sin espacios";
		} else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{3,40}$", this.nombreUsuario, 40)) {
			return "Nombre inválido, sólo se permiten entre 3 y 40 caracteres";
		}  else if (!isValid("^[a-zA-Z0-9_-]{4,40}$", this.username, 40)) {
			return "El nombre de usuario debe contener entre 4 y 40 caracteres sin espacios.";
		}
		return null;
	}

	public String password(){
		if (!isValid("^[a-zA-Z0-9!@#$%^&.*]{4,100}$", this.password, 100)) {
			return "La contraseña debe contener entre 4 y 100 caracteres sin espacios o caracteres especiales";
		}
		return null;
	}
}
