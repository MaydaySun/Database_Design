package com.db_project.utils;

import com.db_project.model.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 初始化sqlSessionFactory，并返回sqlSession
 */
public class MybatisUtils {
    //SqlSessionFactory 对象最好是全局的单例变量
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //每个请求初始化一个SqlSession，默认为非自动提交
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
