package com.Back_Gr42_Eq03.model;

public class Producto {
  
  private Long idProduct;
  private String name;
  private Long idSupplier;
  private Double ivaPurchase;
  private Double priceSale;
  private Double pricePurchase;

  public Producto() {
  }

  public Producto(Long idProduct, String name, Long idSupplier, Double ivaPurchase, Double priceSale, Double pricePurchase) {
    this.idProduct = idProduct;
    this.name = name;
    this.idSupplier = idSupplier;
    this.ivaPurchase = ivaPurchase;
    this.priceSale = priceSale;
    this.pricePurchase = pricePurchase;
  }

  public Long getIdProduct() {
    return this.idProduct;
  }

  public void setIdProduct(Long idProduct) {
    this.idProduct = idProduct;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getIdSupplier() {
    return this.idSupplier;
  }

  public void setIdSupplier(Long idSupplier) {
    this.idSupplier = idSupplier;
  }

  public Double getIvaPurchase() {
    return this.ivaPurchase;
  }

  public void setIvaPurchase(Double ivaPurchase) {
    this.ivaPurchase = ivaPurchase;
  }

  public Double getPriceSale() {
    return this.priceSale;
  }

  public void setPriceSale(Double priceSale) {
    this.priceSale = priceSale;
  }

  public Double getPricePurchase() {
    return this.pricePurchase;
  }

  public void setPricePurchase(Double pricePurchase) {
    this.pricePurchase = pricePurchase;
  }  
}