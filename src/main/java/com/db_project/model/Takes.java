package com.db_project.model;


import java.io.Serializable;

public class Takes implements Serializable {

  private String employeeId;
  private String courseId;
  private long completed;
  private long isPassed;


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
