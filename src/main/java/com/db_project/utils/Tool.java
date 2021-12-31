package com.db_project.utils;

import com.db_project.model.*;

public class Tool {
    public static String toString(Employee employee){
        return employee.getId() + ", " + employee.getName() + ", " + employee.getDeptId();
    }

    public static String toString(Takes takes){
        return takes.getCourseId() + ", " + takes.getEmployeeId();
    }

    public static String toString(TestRecord testRecord){
        return testRecord.getCourseId() + ", " + testRecord.getEmployeeId() + ", "
                + testRecord.getScore();
    }

    public static String toString(Log log){
        return log.getLogId() + ", " + log.getUsername() + ", " + log.getContent() + ", "
                + log.getTime();
    }

    public static String toString(Course course) {
        return course.getCourseId() + ", " + course.getTitle() + ", " + course.getType();
    }
}
