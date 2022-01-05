package com.db_project.dao;

import com.db_project.model.*;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface AdminMapper {
    Employee getEmployee(@Param("id")String id) throws SQLException;
    Department getDept(@Param("dept_id")String dept_id) throws SQLException;

    int updateEmployee(@Param("id")String id,
                       @Param("name")String new_name,
                       @Param("gender")String new_gender,
                       @Param("age")int new_age,
                       @Param("city")String new_city,
                       @Param("phone_number")String new_phone_number,
                       @Param("email")String new_email,
                       @Param("dept_id")String new_dept_id) throws SQLException;

    int addEmployee(@Param("id")String id,
                    @Param("name")String name,
                    @Param("did")String did,
                    @Param("age") int age,
                    @Param("sex") String sex,
                    @Param("email") String email,
                    @Param("city") String city,
                    @Param("phone") String phone) throws SQLException;

    int deleteEmployee(@Param("id")String id) throws SQLException;
    public Course getCourse(@Param("cid")String cid) throws SQLException;
    public int updateCourse(@Param("cid")String cid, @Param("title")String title) throws SQLException;
    public int addCourse(@Param("cid")String cid, @Param("title")String title,
                           @Param("iid")String iid) throws SQLException;
    public int deleteCourse(@Param("cid")String cid) throws SQLException;
    public List<TestRecord> getTestRecords(@Param("id")String id) throws SQLException;
    public Log getLog(@Param("lid")Long lid) throws SQLException;
    public int addLog(@Param("content")String content) throws SQLException;
}
