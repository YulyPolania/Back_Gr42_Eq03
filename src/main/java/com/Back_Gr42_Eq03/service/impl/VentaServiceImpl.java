package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.VentaRepository;
import com.Back_Gr42_Eq03.model.Venta;
import com.Back_Gr42_Eq03.service.api.VentaServiceApi;

@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Long> implements VentaServiceApi {

	@Autowired
	private VentaRepository ventaRepository;

	@Override
	protected CrudRepository<Venta, Long> getDao() {
		return ventaRepository;
	}
}