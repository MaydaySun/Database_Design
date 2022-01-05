package com.db_project.model;


public class Instructor {

  private String employee_id;
  private String name;
  private java.sql.Timestamp date_registered;


  public String getEmployeeId() {
    return employee_id;
  }

  public void setEmployeeId(String employeeId) {
    this.employee_id = employeeId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public java.sql.Timestamp getDateRegistered() {
    return date_registered;
  }

  public void setDateRegistered(java.sql.Timestamp dateRegistered) {
    this.date_registered = dateRegistered;
  }

}
