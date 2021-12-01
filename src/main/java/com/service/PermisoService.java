package com.service;

import java.util.List;

import com.commons.GenericService;
import com.model.Permiso;

public interface PermisoService extends GenericService<Permiso, Integer> {

  List<Permiso> findByCedulaUsuario(Long CedulaUsuario);
}
