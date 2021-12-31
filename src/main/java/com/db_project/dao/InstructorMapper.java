package com.db_project.dao;

import com.db_project.model.Instructor;
import com.db_project.model.Takes;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface InstructorMapper {
    public Instructor getInstructor(@Param("iid")String iid) throws SQLException;
    public List<Takes> getStudents(@Param("iid")String iid) throws SQLException;
    public int addGrade( @Param("cid")String cid, @Param("id")String id, @Param("score")Long score) throws SQLException;
}
