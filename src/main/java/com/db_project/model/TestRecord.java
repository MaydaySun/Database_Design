package com.db_project.model;


public class TestRecord {

  private Long record_id;
  private String course_id;
  private String employee_id;
  private Long score;


  public long getRecordId() {
    return record_id;
  }

  public void setRecordId(long recordId) {
    this.record_id = recordId;
  }


  public String getCourseId() {
    return course_id;
  }

  public void setCourseId(String courseId) {
    this.course_id = courseId;
  }


  public String getEmployeeId() {
    return employee_id;
  }

  public void setEmployeeId(String employeeId) {
    this.employee_id = employeeId;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

}
