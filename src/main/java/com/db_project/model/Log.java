package com.db_project.model;


public class Log {

  private Long log_id;
  private String employee_id;
  private String content;
  private java.sql.Timestamp time;


  public long getLogId() {
    return log_id;
  }

  public void setLogId(long logId) {
    this.log_id = logId;
  }


  public String getEmployee_id(){
    return this.employee_id;
  }

  public void setEmployee_id(String employee_id) {
    this.employee_id = employee_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }

}
