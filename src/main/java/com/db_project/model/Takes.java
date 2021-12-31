package com.db_project.model;


public class Takes {

  private String employeeId;
  private String courseId;
  private Long completed;
  private Long isPassed;


  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public long getCompleted() {
    return completed;
  }

  public void setCompleted(long completed) {
    this.completed = completed;
  }


  public long getIsPassed() {
    return isPassed;
  }

  public void setIsPassed(long isPassed) {
    this.isPassed = isPassed;
  }

}
