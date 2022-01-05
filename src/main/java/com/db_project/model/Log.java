package com.db_project.model;


public class Log {

  private Long log_id;
  private String username;
  private String content;
  private java.sql.Timestamp time;


  public long getLogId() {
    return log_id;
  }

  public void setLogId(long logId) {
    this.log_id = logId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
