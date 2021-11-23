package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Role;
import com.service.RoleService;




@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolController {

	
	
	@Autowired 
	private RoleService roleService;

	@GetMapping(value = "/all")
	public List<Role> getAll() {
		return roleService.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Role find(@PathVariable Integer id) {
		return roleService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Role> save(@RequestBody Role role) {
		Role obj = roleService.save(role);
		return new ResponseEntity<Role>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Role> delete(@PathVariable Integer id) {
		Role role = roleService.get(id);
		if (role != null) {
			return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
}
	
	
	
