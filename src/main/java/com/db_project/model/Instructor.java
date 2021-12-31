package com.db_project.model;


public class Instructor {

  private String employeeId;
  private String name;
  private java.sql.Timestamp dateRegistered;


  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public java.sql.Timestamp getDateRegistered() {
    return dateRegistered;
  }

  public void setDateRegistered(java.sql.Timestamp dateRegistered) {
    this.dateRegistered = dateRegistered;
  }

}
