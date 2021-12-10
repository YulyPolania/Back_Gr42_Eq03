package com.service;

import java.util.List;

import com.commons.GenericService;
import com.model.Venta;

public interface VentaService extends GenericService<Venta, Long> {

  List<Venta> findByCedulaCliente(Long cedulaCliente);

  Double[] getTotalVentas();

  List<String[]> getVentasByCliente();

  List<Long> getAllCodigoVenta();

}
