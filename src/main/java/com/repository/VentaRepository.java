package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import com.model.Venta;

public interface VentaRepository extends MongoRepository<Venta, Long> {

  @Query
  List<Venta> findByCedulaCliente(Long cedulaCliente);

  @Query
  List<Venta> findByIdSede(Integer idSede);

}
