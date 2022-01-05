package com.db_project.model;


public class Manager {

  private String employee_id;
  private String name;
  private String deptId;


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


  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

}
