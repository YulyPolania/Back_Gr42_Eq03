package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Producto;
import com.repository.ProductoRepository;
import com.service.ProductoService;

@Service
public class ProductoServiceImpl extends GenericImpl<Producto, Long> implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	protected CrudRepository<Producto, Long> getDao() {
		return productoRepository;
	}

}
