package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Venta;
import com.repository.VentaRepository;
import com.service.VentaService;

@Service
public class VentaServiceImpl extends GenericImpl<Venta, Long> implements VentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Override
	protected CrudRepository<Venta, Long> getDao() {
		return ventaRepository;
	}

}
