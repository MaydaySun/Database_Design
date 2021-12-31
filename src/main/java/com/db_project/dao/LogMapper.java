package com.db_project.dao;

import com.db_project.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface LogMapper {
    public int addLog(@Param("id")String id, @Param("content")String content) throws SQLException;
}
