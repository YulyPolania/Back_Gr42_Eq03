package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private Long cedulaCliente;
    private String direccionCliente;
    private String emailCliente;
    private String nombreCliente;
    private String telefonoCliente;

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
        if (!isValid("^\\d{1,15}$", this.cedulaCliente.toString(), 15)) {
            return "La cédula del cliente sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
        } else if (!isValid("^[a-zA-ZÀ-ÿ0-9!@#$%^&.*\\s]{8,100}$", this.direccionCliente, 100)) {
            return "La dirección debe contener entre 8 y 100 caracteres máximo";
        } else if (!isValid("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9]{2,6}$", this.emailCliente, 60)) {
            return "Correo electrónico inválido, se permiten hata 60 caracteres, sin espacios";
        } else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{3,40}$", this.nombreCliente, 40)) {
            return "Nombre inválido, sólo se permiten entre 3 y 40 caracteres";
        } else if (!isValid("^[a-zA-Z0-9\\s_-]{8,40}$", this.telefonoCliente, 40)) {
            return "El teléfono debe contener entre 8 y 40 caracteres.";
        }
        return null;
    }
}
