package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.SucursalRepository;
import com.Back_Gr42_Eq03.model.Sucursal;
import com.Back_Gr42_Eq03.service.api.SucursalServiceApi;

@Service
public class SucursalServiceImpl extends GenericServiceImpl<Sucursal, Long> implements SucursalServiceApi {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	protected CrudRepository<Sucursal, Long> getDao() {
		return sucursalRepository;
	}
}