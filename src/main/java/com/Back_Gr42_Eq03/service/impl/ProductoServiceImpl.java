package com.Back_Gr42_Eq03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Back_Gr42_Eq03.commons.GenericServiceImpl;
import com.Back_Gr42_Eq03.dao.api.ProductoRepository;
import com.Back_Gr42_Eq03.model.Producto;
import com.Back_Gr42_Eq03.service.api.ProductoServiceApi;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Long> implements ProductoServiceApi {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	protected CrudRepository<Producto, Long> getDao() {
		return productoRepository;
	}
}