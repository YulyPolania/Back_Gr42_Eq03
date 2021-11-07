package com.Back_Gr42_Eq03.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "ventas")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idSale;

	private Long idUser;
	private Long idCustomer;
	private List<DetalleVenta> SaleDetails;
	private Double ivaSale;
	private Double totalIva;
	private Double totalSale;
	private Long idSucursal;
	private Date dateSale;

	public Venta() {
	}

	public Venta(Long idSale, Long idUser, Usuario user, Long idCustomer, Cliente customer,
			List<DetalleVenta> SaleDetails, Double ivaSale, Double totalIva, Double totalSale, Long idSucursal,
			Sucursal sucursal, Date dateSale) {
		this.idSale = idSale;
		this.idUser = idUser;
		this.idCustomer = idCustomer;
		this.SaleDetails = SaleDetails;
		this.ivaSale = ivaSale;
		this.totalIva = totalIva;
		this.totalSale = totalSale;
		this.idSucursal = idSucursal;
		this.dateSale = dateSale;
	}

	public Long getIdSale() {
		return this.idSale;
	}

	public void setIdSale(Long idSale) {
		this.idSale = idSale;
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdCustomer() {
		return this.idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public List<DetalleVenta> getSaleDetails() {
		return this.SaleDetails;
	}

	public void setSaleDetails(List<DetalleVenta> SaleDetails) {
		this.SaleDetails = SaleDetails;
	}

	public Double getIvaSale() {
		return this.ivaSale;
	}

	public void setIvaSale(Double ivaSale) {
		this.ivaSale = ivaSale;
	}

	public Double getTotalIva() {
		return this.totalIva;
	}

	public void setTotalIva(Double totalIva) {
		this.totalIva = totalIva;
	}

	public Double getTotalSale() {
		return this.totalSale;
	}

	public void setTotalSale(Double totalSale) {
		this.totalSale = totalSale;
	}

	public Long getIdSucursal() {
		return this.idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Date getDateSale() {
		return this.dateSale;
	}

	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
	}
}