package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.model.Permiso;
import com.repository.PermisoRepository;
import com.service.PermisoService;

@Service
public class PermisoServiceImpl extends GenericImpl<Permiso, Integer> implements PermisoService {

	@Autowired
	private PermisoRepository permisoRepository;

	@Override
	protected CrudRepository<Permiso, Integer> getDao() {
		return permisoRepository;
	}

}
