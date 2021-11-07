package com.Back_Gr42_Eq03.model;

public class Sucursal {

  private Long idSucursal;
  private String name;
  private String city;
  private String address;
  private String phoneNumber;

  public Sucursal() {
  }

  public Sucursal(Long idSucursal, String name, String city, String address, String phoneNumber) {
    this.idSucursal = idSucursal;
    this.name = name;
    this.city = city;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public Long getIdSucursal() {
    return this.idSucursal;
  }

  public void setIdSucursal(Long idSucursal) {
    this.idSucursal = idSucursal;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
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
}