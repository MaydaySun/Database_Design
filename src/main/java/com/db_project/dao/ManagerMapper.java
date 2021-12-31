package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {
    Manager getManagerByUid(@Param("employee_id")String id);
    List<Employee> getEmployeesByDeptId(@Param("dept_id")String deptId);
    List<Course> getCoursesByDeptId(@Param("dept_id")String deptId);

}
