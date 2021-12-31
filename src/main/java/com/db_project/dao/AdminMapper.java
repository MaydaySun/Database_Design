package com.db_project.dao;

import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Log;
import com.db_project.model.TestRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    public Employee getEmployee(@Param("id")String id);
    public int updateEmployee(@Param("id")String id, @Param("name")String new_name);
    public int addEmployee(@Param("id")String id, @Param("name")String new_name,
                           @Param("did")String did);
    public int deleteEmployee(@Param("id")String id);
    public Course getCourse(@Param("cid")String cid);
    public int updateCourse(@Param("cid")String cid, @Param("title")String title);
    public int addCourse(@Param("cid")String cid, @Param("title")String title,
                           @Param("iid")String iid);
    public int deleteCourse(@Param("cid")String cid);
    public List<TestRecord> getTestRecords(@Param("id")String id);
    public Log getLog(@Param("lid")String lid);
    public int addLog(@Param("content")String content);
}
