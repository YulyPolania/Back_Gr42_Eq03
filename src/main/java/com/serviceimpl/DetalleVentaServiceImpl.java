package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.DetalleVenta;
import com.repository.DetalleVentaRepository;
import com.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl extends GenericImpl<DetalleVenta, Long> implements DetalleVentaService {

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;

	@Override
	protected CrudRepository<DetalleVenta, Long> getDao() {
		return detalleVentaRepository;
	}

}
