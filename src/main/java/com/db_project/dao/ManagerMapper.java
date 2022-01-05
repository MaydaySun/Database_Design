package com.db_project.dao;

import com.db_project.model.*;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    Manager getManagerByUid(@Param("id")String id) throws SQLException;


    List<Employee> getEmployeesByDeptId(@Param("dept_id")String deptId) throws SQLException;


    List<Map<String, Object>> getCoursesByDeptId(@Param("dept_id")String deptId) throws SQLException;


    int hasTaken(@Param("id") String id,
                 @Param("course_id")String courseId) throws SQLException;
    Course getCourseByCid(@Param("course_id") String id) throws SQLException;
    Department getDeptByCid(@Param("course_id") String id) throws SQLException;
    void addCourseToEmployee(@Param("id")String id,
                             @Param("cid")String courseId) throws SQLException;


    List<Map<String, Object>> getTestRecords(@Param("id")String id) throws SQLException;


    Employee getEmployeeByIdAndDeptId(@Param("id")String id,
                                      @Param("dept_id")String deptId) throws SQLException;
    int getUnpassedCompulsoryCourseCnt(@Param("id")String id,
                                       @Param("dept_id")String dept_id) throws SQLException;
    void moveToNewDept(@Param("id")String id,
                       @Param("dept_id")String newDepartmentId) throws SQLException;

    List<String> getUnfinishedCompulsoryCourseID(@Param("dept_id") String dept_id,
                                       @Param("id") String id) throws SQLException;
    void addNewTakes(@Param("id") String id,
                     @Param("course_id") String courseId) throws SQLException;

    List<Map<String, Object>> getPassedTakes(@Param("dept_id")String deptId) throws SQLException;

    List<Map<String, Object>> getEmployeeWithSituation(@Param("course_id") String courseId,
                                                       @Param("dept_id") String dept_id,
                                                       @Param("is_passed") int situation) throws SQLException;
    List<Map<String, Object>> getCourseScores(@Param("course_id") String courseId,
                                              @Param("dept_id") String dept_id) throws SQLException;


    List<Map<String, Object>> getAllTakes(@Param("dept_id")String deptId) throws SQLException;

    List<Map<String, Object>> getFailedTimes(@Param("course_id")String courseId) throws SQLException;

    List<Employee> getShiftableEmployee(@Param("dept_id")String deptId) throws SQLException;

    List<Map<String, Object>> getNewCourse(@Param("id")String id,
                                           @Param("dept_id")String newDeptId) throws SQLException;

}
