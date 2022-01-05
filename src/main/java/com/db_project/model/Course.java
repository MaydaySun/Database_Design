package com.db_project.model;


public class Course {

  private String courseId;
  private String title;
  private String type;
  private String content;
  private String instructorId;

  private CourseToDept courseToDept;
  private Instructor instructor;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(String instructorId) {
    this.instructorId = instructorId;
  }

  public CourseToDept getCourseToDept(){
    return this.courseToDept;
  }

  public void setCourseToDept(CourseToDept courseToDept){
    this.courseToDept = courseToDept;
  }

  public Instructor getInstructor(){
    return this.instructor;
  }

}
