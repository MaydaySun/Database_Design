package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.TestRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    Employee getEmployeeById(@Param("id")String id);
    int update(@Param("id")String id, @Param("new_name")String new_name);
    List<Course> getCourses(@Param("id")String id);
    List<TestRecord> getTestRecords(@Param("id")String id);
}
