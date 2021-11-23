package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String telSede;
}
