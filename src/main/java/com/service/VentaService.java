package com.service;

import java.util.List;

import com.commons.GenericService;
import com.model.Venta;

public interface VentaService extends GenericService<Venta, Long> {

  List<Venta> findByCedulaCliente(Long cedulaCliente);

  List<Venta> findByIdSede(Integer idSede);

  List<String[]> getVentasByCliente();

  List<String[]> getVentasBySede();

  List<Long> getAllCodigoVenta();

}
