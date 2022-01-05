package com.db_project.model;


public class Course {

  private String course_id;
  private String title;
  private String type;
  private String content;
  private String instructor_id;

  private CourseToDept course_to_dept;
  private Instructor instructor;


  public String getCourseId() {
    return course_id;
  }

  public void setCourseId(String courseId) {
    this.course_id = courseId;
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
    return instructor_id;
  }

  public void setInstructorId(String instructorId) {
    this.instructor_id = instructorId;
  }

  public CourseToDept getCourseToDept(){
    return this.course_to_dept;
  }

  public void setCourseToDept(CourseToDept courseToDept){
    this.course_to_dept = courseToDept;
  }

  public Instructor getInstructor(){
    return this.instructor;
  }

}
