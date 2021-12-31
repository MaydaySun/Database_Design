package com.db_project.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    public Employee getEmployeeById(@Param("id")String id);
    public int update(@Param("id")String id, @Param("new_name")String new_name);
    public List<Course> getCourses(@Param("id")String id);
    public List<TestRecord> getTestRecords(@Param("id")String id);
}
