package com.Back_Gr42_Eq03.model;

import org.springframework.data.annotation.Id;

public class Proveedor {

  @Id
  private Long idSupplier;

  private String name;
  private String address;
  private String phoneNumber;
  private String city;

  public Proveedor() {
  }

  public Proveedor(Long idSupplier, String name, String address, String phoneNumber, String city) {
    this.idSupplier = idSupplier;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.city = city;
  }

  public Long getIdSupplier() {
    return this.idSupplier;
  }

  public void setIdSupplier(Long idSupplier) {
    this.idSupplier = idSupplier;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }  
}