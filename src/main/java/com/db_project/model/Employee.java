package com.db_project.model;


import java.io.Serializable;

public class Employee implements Serializable {

  private String id;
  private String name;
  private String gender;
  private long age;
  private java.sql.Timestamp dateEmployed;
  private String city;
  private double phoneNumber;
  private String email;
  private long departmentId;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public java.sql.Timestamp getDateEmployed() {
    return dateEmployed;
  }

  public void setDateEmployed(java.sql.Timestamp dateEmployed) {
    this.dateEmployed = dateEmployed;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public double getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(double phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(long departmentId) {
    this.departmentId = departmentId;
  }


}
