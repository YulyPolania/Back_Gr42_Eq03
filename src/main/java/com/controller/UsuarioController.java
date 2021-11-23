package com.controller;

import java.util.List;

import com.model.Usuario;
import com.service.UsuarioService;

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
@RequestMapping("/usuarios")
@CrossOrigin("*")


public class UsuarioController {
			
		
		@Autowired 
		private UsuarioService usuarioService;
		@Autowired 
		private BCryptPasswordEncoder bCryptPasswordEncoder;

		@GetMapping(value = "/all")
		public ResponseEntity<?> getAll() {
			List<Usuario> users = usuarioService.getAll();
			if (users != null) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		@GetMapping(value = "/find/{id}")
		public Usuario find(@PathVariable Long id) {
			return usuarioService.get(id);
		}
		

		@PostMapping(value = "/save")
		public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			Usuario obj = usuarioService.save(usuario);
			return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
		}

		@GetMapping(value = "/delete/{id}")
		public ResponseEntity<Usuario> delete(@PathVariable Long id) {
			Usuario usuario = usuarioService.get(id);
			if (usuario != null) {
				return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}

}
