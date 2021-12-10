package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.commons.GenericImpl;
import com.model.Cliente;
import com.model.Sede;
import com.model.Venta;
import com.repository.ClienteRepository;
import com.repository.SedeRepository;
import com.repository.VentaRepository;
import com.service.VentaService;

@Service
public class VentaServiceImpl extends GenericImpl<Venta, Long> implements VentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private SedeRepository sedeRepository;

	@Override
	protected CrudRepository<Venta, Long> getDao() {
		return ventaRepository;
	}

	@Override
	public List<Venta> findByCedulaCliente(Long cedulaCliente) {
		return ventaRepository.findByCedulaCliente(cedulaCliente);
	}

	@Override
	public List<String[]> getVentasByCliente() {
		List<String[]> resultado = new ArrayList<String[]>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente i : clientes) {
			List<Venta> ventasCliente = ventaRepository.findByCedulaCliente(i.getCedulaCliente());
			Double sum = 0.0;
			for (Venta j : ventasCliente) {
				sum += j.getTotalIva();
			}
			String[] registro = { i.getNombreCliente(), sum.toString() };
			resultado.add(registro);
		}
		return resultado;
	}

	@Override
	public Double[] getTotalVentas() {
		List<Venta> ventas = ventaRepository.findAll();
		Double venta = 0.0;
		Double iva = 0.0;
		Double total = 0.0;
		for (Venta i : ventas) {
			venta += i.getTotalVenta();
			iva += i.getIvaVenta();
			total += i.getTotalIva();
		}
		Double[] resultado = { venta, iva, total };
		return resultado;
	}

	@Override
	public List<Long> getAllCodigoVenta() {
		List<Long> allIds = new ArrayList<Long>();
		for (Venta i : ventaRepository.findAll()) {
			allIds.add(i.getCodigoVenta());
		}
		return allIds;
	}

}
