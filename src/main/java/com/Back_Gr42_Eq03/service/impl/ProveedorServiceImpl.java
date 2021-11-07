package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.ProveedorRepository;
import com.Back_Gr42_Eq03.model.Proveedor;
import com.Back_Gr42_Eq03.service.api.ProveedorServiceApi;

@Service
public class ProveedorServiceImpl extends GenericServiceImpl<Proveedor, Long> implements ProveedorServiceApi {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	protected CrudRepository<Proveedor, Long> getDao() {
		return proveedorRepository;
	}
}