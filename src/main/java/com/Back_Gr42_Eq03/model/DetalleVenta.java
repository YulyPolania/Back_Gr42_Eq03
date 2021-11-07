package com.Back_Gr42_Eq03.model;

public class DetalleVenta {

  private Long idDetailSale;

  private Long idProduct;
  private Long idSale;
  private Integer quantity;
  private Double totalValue;
  private Double valueIva;
  private Double totalDetailSale;

  public DetalleVenta() {
  }

  public DetalleVenta(Long idDetailSale, Long idProduct, Long idSale, Integer quantity, Double totalValue,
      Double valueIva, Double totalDetailSale) {
    this.idDetailSale = idDetailSale;
    this.idProduct = idProduct;
    this.idSale = idSale;
    this.quantity = quantity;
    this.totalValue = totalValue;
    this.valueIva = valueIva;
    this.totalDetailSale = totalDetailSale;
  }

  public Long getIdDetailSale() {
    return this.idDetailSale;
  }

  public void setIdDetailSale(Long idDetailSale) {
    this.idDetailSale = idDetailSale;
  }

  public Long getIdProduct() {
    return this.idProduct;
  }

  public void setIdProduct(Long idProduct) {
    this.idProduct = idProduct;
  }

  public Long getIdSale() {
    return this.idSale;
  }

  public void setIdSale(Long idSale) {
    this.idSale = idSale;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getTotalValue() {
    return this.totalValue;
  }

  public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
  }

  public Double getValueIva() {
    return this.valueIva;
  }

  public void setValueIva(Double valueIva) {
    this.valueIva = valueIva;
  }

  public Double getTotalDetailSale() {
    return this.totalDetailSale;
  }

  public void setTotalDetailSale(Double totalDetailSale) {
    this.totalDetailSale = totalDetailSale;
  }
}