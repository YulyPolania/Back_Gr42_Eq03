package com.Back_Gr42_Eq03.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "clientes")
public class Cliente {

  @Id
  private Long idCustomer;

  private String name;
  private String address;
  private String email;
  private String phoneNumber;

  public Cliente() {
  }

  public Cliente(Long idCustomer, String name, String address, String email, String phoneNumber) {
    this.idCustomer = idCustomer;
    this.name = name;
    this.address = address;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Long getIdCustomer() {
    return this.idCustomer;
  }

  public void setIdCustomer(Long idCustomer) {
    this.idCustomer = idCustomer;
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}