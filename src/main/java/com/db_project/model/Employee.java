package com.db_project.model;


public class Employee {

  private String id;
  private String name;
  private String gender;
  private Long age;
  private java.sql.Timestamp date_employed;
  private String city;
  private String phone_number;
  private String email;
  private String dept_id;

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
    return date_employed;
  }

  public void setDateEmployed(java.sql.Timestamp dateEmployed) {
    this.date_employed = dateEmployed;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getPhoneNumber() {
    return phone_number;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phone_number = phoneNumber;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getDeptId() {
    return dept_id;
  }

  public void setDeptId(String deptId) {
    this.dept_id = deptId;
  }


}
