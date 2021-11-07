package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.ClienteRepository;
import com.Back_Gr42_Eq03.model.Cliente;
import com.Back_Gr42_Eq03.service.api.ClienteServiceApi;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Long> implements ClienteServiceApi {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	protected CrudRepository<Cliente, Long> getDao() {
		return clienteRepository;
	}
}