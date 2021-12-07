package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Permiso> findByCedulaUsuario(Long CedulaUsuario) {
		return permisoRepository.findByCedulaUsuario(CedulaUsuario);
	}

	@Override
	public List<Integer> getAllIdPermisos() {
		List<Integer> allIds = new ArrayList<Integer>();
		for (Permiso i : permisoRepository.findAll()) {
			allIds.add(i.getIdPermiso());
		}
		return allIds;
	}

	@Override
	public Boolean isPermiso(Permiso permiso) {
		List<Permiso> list = permisoRepository.findByCedulaUsuario(permiso.getCedulaUsuario());
		for (Permiso i : list) {
			if (i.getIdRol() == permiso.getIdRol() && i.getIdSede() == permiso.getIdSede()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteByCedulaUsuario(Long cedulaUsuario) {
		List<Permiso> list = permisoRepository.findByCedulaUsuario(cedulaUsuario);
		for (Permiso i : list) {
			permisoRepository.deleteById(i.getIdPermiso());
		}		
	}
}
