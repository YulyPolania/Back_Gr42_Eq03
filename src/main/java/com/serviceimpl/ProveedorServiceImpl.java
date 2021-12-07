package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Proveedor;
import com.repository.ProveedorRepository;
import com.service.ProveedorService;

@Service
public class ProveedorServiceImpl extends GenericImpl<Proveedor, Long> implements ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	protected CrudRepository<Proveedor, Long> getDao() {
		return proveedorRepository;
	}

}
