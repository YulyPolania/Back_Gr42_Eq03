package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Cliente;
import com.repository.ClienteRepository;
import com.service.ClienteService;

@Service
public class ClienteServiceImpl extends GenericImpl<Cliente, Long> implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	protected CrudRepository<Cliente, Long> getDao() {
		return clienteRepository;
	}

}
