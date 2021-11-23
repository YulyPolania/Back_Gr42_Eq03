package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario{
	@Id
	private Long cedulaUsuario;
	
	private String emailUsuario;
	
	private String nombreUsuario;
	
	private String password;
	
	private String username;
	
	

	
}
