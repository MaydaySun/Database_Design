package com.db_project.model;


public class CourseToDept {

  private String courseId;
  private String deptId;
  private String required;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }


  public String getRequired() {
    return required;
  }

  public void setRequired(String required) {
    this.required = required;
  }

}
