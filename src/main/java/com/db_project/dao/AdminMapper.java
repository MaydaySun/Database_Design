package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Log;
import com.db_project.model.TestRecord;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface AdminMapper {
    public Employee getEmployee(@Param("id")String id) throws SQLException;
    public int updateEmployee(@Param("id")String id, @Param("name")String new_name) throws SQLException;
    public int addEmployee(@Param("id")String id, @Param("name")String new_name,
                           @Param("did")String did) throws SQLException;
    public int deleteEmployee(@Param("id")String id) throws SQLException;
    public Course getCourse(@Param("cid")String cid) throws SQLException;
    public int updateCourse(@Param("cid")String cid, @Param("title")String title) throws SQLException;
    public int addCourse(@Param("cid")String cid, @Param("title")String title,
                           @Param("iid")String iid) throws SQLException;
    public int deleteCourse(@Param("cid")String cid) throws SQLException;
    public List<TestRecord> getTestRecords(@Param("id")String id) throws SQLException;
    public Log getLog(@Param("lid")Long lid) throws SQLException;
    public int addLog(@Param("content")String content) throws SQLException;
}
