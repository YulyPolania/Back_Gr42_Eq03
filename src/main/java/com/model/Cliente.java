package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")


@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Cliente {
	
    @Id
	private Long cedulaCliente;
    
	private String dirCliente;
	private String emailCliente;
    private String nombreCliente;
    private String telefonoCliente;

}
