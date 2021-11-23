package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
