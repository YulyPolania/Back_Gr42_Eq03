package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Sede;
import com.repository.SedeRepository;
import com.service.SedeService;

@Service
public class SedeServiceImpl extends GenericImpl<Sede, Integer> implements SedeService{
	
	@Autowired
	private SedeRepository sedeRepository;
	
	@Override
	protected CrudRepository<Sede, Integer> getDao() {
		return sedeRepository;
	}

}
