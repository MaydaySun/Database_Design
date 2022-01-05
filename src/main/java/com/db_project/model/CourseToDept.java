package com.db_project.model;


public class CourseToDept {

  private String course_id;
  private String dept_id;
  private String required;


  public String getCourseId() {
    return course_id;
  }

  public void setCourseId(String courseId) {
    this.course_id = courseId;
  }


  public String getDeptId() {
    return dept_id;
  }

  public void setDeptId(String deptId) {
    this.dept_id = deptId;
  }


  public String getRequired() {
    return required;
  }

  public void setRequired(String required) {
    this.required = required;
  }

}
