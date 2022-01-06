package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Instructor;
import com.db_project.model.Takes;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InstructorMapper {
    public Instructor getInstructor(@Param("iid")String iid) throws SQLException;
    public Course getCourse(@Param("cid")String cid,
                            @Param("iid")String iid) throws SQLException;
    public Takes getStudent(@Param("id")String id, @Param("cid")String cid) throws SQLException;
    List<Map<String, Object>> getStudents(@Param("iid")String iid) throws SQLException;
    public int setCompleted(@Param("id")String id, @Param("cid")String cid) throws SQLException;
    public int setPassed(@Param("id")String id, @Param("cid")String cid) throws SQLException;
    public int setFailed(@Param("id")String id, @Param("cid")String cid) throws SQLException;
    public int addTestRecord( @Param("cid")String cid, @Param("id")String id, @Param("score")Long score) throws SQLException;
    public int associateCourse(@Param("cid")String cid, @Param("did")String did, @Param("required")String required) throws SQLException;
    public List<Employee> getEmployeesByDid(@Param("did")String did) throws SQLException;
    public int addTakes(@Param("id")String id, @Param("cid")String cid) throws SQLException;
}
