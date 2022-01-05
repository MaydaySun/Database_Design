package com.db_project.model;


public class Takes {

  private String employee_id;
  private String course_id;
  private Long completed;
  private Long is_passed;


  public String getEmployeeId() {
    return employee_id;
  }

  public void setEmployeeId(String employeeId) {
    this.employee_id = employeeId;
  }


  public String getCourseId() {
    return course_id;
  }

  public void setCourseId(String courseId) {
    this.course_id = courseId;
  }


  public long getCompleted() {
    return completed;
  }

  public void setCompleted(long completed) {
    this.completed = completed;
  }


  public long getIsPassed() {
    return is_passed;
  }

  public void setIsPassed(long isPassed) {
    this.is_passed = isPassed;
  }

}
