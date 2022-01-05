package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Department;
import com.db_project.model.Employee;
import com.db_project.model.TestRecord;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmployeeById(@Param("id")String id) throws SQLException;
    Department getDept(@Param("id")String dept_id) throws SQLException;

    void update(@Param("id")String id,
               @Param("new_name")String new_name,
               @Param("gender") String new_gender,
               @Param("age") int new_age,
               @Param("city") String new_city,
               @Param("email") String new_email,
               @Param("phone") String new_phone) throws SQLException;

    List<Map<String, Object>> getCourses(@Param("id")String id) throws SQLException;

    List<Map<String, Object>> getTestRecords(@Param("id")String id) throws SQLException;
}
