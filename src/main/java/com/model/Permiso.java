package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Integer idsede;
	private  Long idUsuario;
	
	

}
