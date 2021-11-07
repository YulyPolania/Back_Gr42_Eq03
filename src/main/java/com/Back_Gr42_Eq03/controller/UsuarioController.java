package com.Back_Gr42_Eq03.controller;

import java.util.List;

import com.Back_Gr42_Eq03.model.Usuario;
import com.Back_Gr42_Eq03.service.api.UsuarioServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/usuarios/v1")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioServiceApi usuarioServiceApi;

	@GetMapping(value = "/all")
	public List<Usuario> getAll() {
		return usuarioServiceApi.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Usuario find(@PathVariable Long id) {
		return usuarioServiceApi.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		BCryptPasswordEncoder passGen = new BCryptPasswordEncoder();
		usuario.setPassword(passGen.encode(usuario.getPassword()));
		Usuario obj = usuarioServiceApi.save(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
		Usuario usuario = usuarioServiceApi.get(id);
		if (usuario != null) {
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}