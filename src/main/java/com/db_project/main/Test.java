package com.db_project.main;

import com.db_project.dao.InstructorMapper;
import com.db_project.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class Test { //TODO 测试trigger有效性
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);
            instructorMapper.addTestRecord("35142", "10231106003", (long)60);
            sqlSession.commit();
        }catch (SQLException e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
