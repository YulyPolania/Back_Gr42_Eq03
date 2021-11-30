package com.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.model.Permiso;
import com.model.Sede;
import com.model.Usuario;

import com.service.PermisoService;
import com.service.SedeService;
import com.service.UsuarioService;

@Component
public class AdditionalnfoToken implements TokenEnhancer {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private SedeService sedeService;

	@Autowired
	private PermisoService permisoService;

	// ---------extraer listado de permisos por usuario-------------------
	public List<Permiso> getPermisos(String username) {
		Usuario user = usuarioService.findByUserName(username);
		Long id = user.getCedulaUsuario();
		List<Permiso> permisos = new ArrayList<Permiso>();
		for (Permiso registro : permisoService.getAll()) {
			if (registro.getCedulaUsuario().equals(id)) {
				permisos.add(registro);
			}
		}
		return permisos;
	}
	// -------------fin extraer listado de permisos por usuario-------------------

	// ---------extraer listado de sedes por usuario-------------------
	public List<String> getSedes(String username) {
		Usuario user = usuarioService.findByUserName(username);
		Long id = user.getCedulaUsuario();
		List<String> sedes = new ArrayList<String>();
		for (Permiso registro : permisoService.getAll()) {
			if (registro.getCedulaUsuario().equals(id)) {
				for (Sede sede : sedeService.getAll()) {
					if (sede.getIdSede().equals(registro.getIdSede())) {
						sedes.add(sede.getCiudadSede());
					}
				}
			}
		}
		return sedes;
	}
	// -------------fin extraer listado de sedes por usuario-------------------

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		info.put("sedes", getSedes(authentication.getName()).toArray());
		info.put("permisos", getPermisos(authentication.getName()).toArray());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
