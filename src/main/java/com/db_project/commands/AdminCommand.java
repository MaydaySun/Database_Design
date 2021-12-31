package com.db_project.commands;

import com.db_project.dao.AdminMapper;
import com.db_project.main.ArgNotFoundException;
import com.db_project.main.Param;
import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Log;
import com.db_project.model.TestRecord;
import com.db_project.utils.MybatisUtils;
import com.db_project.utils.Tool;
import org.apache.ibatis.session.SqlSession;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import javax.print.attribute.standard.NumberUp;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminCommand {

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            try {
                switch (args[0]){
                    case "getEmployee":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println(Tool.toString(employee));
                    }
                    //TODO get employee's info by id
                    break;
                    case "updateEmployee":{
                        String id = param.getUid();
                        String name = param.getName();
                        adminMapper.updateEmployee(id, name);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO update employee name by id
                        break;
                    case "addEmployee":{
                        String id = param.getUid();
                        String name = param.getName();
                        String did = param.getDid();
                        adminMapper.addEmployee(id, name, did);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 添加员工（需要参数：id, name, did）
                        break;
                    case "deleteEmployee":{
                        String id = param.getUid();
                        adminMapper.deleteCourse(id);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 删除员工 by id
                    case "getCourse":{
                        String cid = param.getCid();
                        Course course = adminMapper.getCourse(cid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println(Tool.toString(course));
                    }
                        //TODO get course by cid
                        break;
                    case "updateCourse":{
                        String cid = param.getCid();
                        String title = param.getTitle();
                        adminMapper.updateCourse(cid, title);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO update course title by cid
                        break;
                    case "addCourse":{
                        String cid = param.getCid();
                        String title = param.getTitle();
                        String iid = param.getIid();
                        adminMapper.addCourse(cid, title, iid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 添加课程(参数：cid, title, instructor)
                        break;
                    case "deleteCourse":{
                        String cid = param.getCid();
                        adminMapper.deleteCourse(cid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 删除课程（参数：cid）
                        break;
                    case "getInfo":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);
                        System.out.println(Tool.toString(employee));
                        List<TestRecord> testRecords = adminMapper.getTestRecords(id);
                        sqlSession.commit();
                        sqlSession.close();
                        for (TestRecord testRecord : testRecords) {
                            System.out.println(Tool.toString(testRecord));
                        }
                    }
                        //TODO 获取员工的test records（参数：id）
                        // 分别调用getEmployee和getTestRecords来获取相关信息
                        break;
                    case "getLog":{
                        Long lid = Long.parseLong(param.getLid());
                        Log log = adminMapper.getLog(lid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println(Tool.toString(log));
                    }
                        //TODO get a single log by lid，parse lid from String to Long first
                        break;
                    case "addLog":{
                        String content = param.getContent();
                        adminMapper.addLog(content);
                        System.out.println("Finished!");
                        sqlSession.commit();
                        sqlSession.close();
                    }
                        //TODO 添加log（参数：content）
                        break;
                    case "logout":
                        sqlSession.close();
                        return;// 登出，返回到Main.console()中等待下一次登录
                    default:
                        sqlSession.close();
                        System.out.println("请输入合法指令");
                }
            }
            catch (SQLException e){
                sqlSession.rollback();// sql执行失败，进行回滚
                sqlSession.close();
                System.err.println("Sql failed: " + e.getSQLState());
                e.printStackTrace();
            }
            catch (ArgNotFoundException | NumberFormatException e){
                System.err.println("Necessary args not found for your instruction");
                e.printStackTrace();
                sqlSession.close();
            }
        }
    }
}
