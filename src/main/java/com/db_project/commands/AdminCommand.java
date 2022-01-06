package com.db_project.commands;

import com.db_project.dao.AdminMapper;
import com.db_project.dao.LogMapper;
import com.db_project.main.ArgNotFoundException;
import com.db_project.main.Param;
import com.db_project.model.*;
import com.db_project.utils.MybatisUtils;
import com.db_project.utils.PrintingTool;
import org.apache.ibatis.session.SqlSession;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminCommand {

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            try {
                parser.parseArgument(Arrays.copyOfRange(args, 1, args.length));
            }catch (CmdLineException e){
                System.out.println("invalid args");
                e.printStackTrace();
                continue;
            }

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            try {
                switch (args[0]){
                    case "getEmployee":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);
                        Department dept = adminMapper.getDept(employee.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();
                        PrintingTool.wrapToTable(employee, dept, true);
                    }
                    //TODO get employee's info by id
                    break;
                    case "updateEmployee":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);

                        String name = param.getNullableName() == null ? employee.getName() : param.getName();
                        String sex = param.getSex() == null ? employee.getGender() : param.getSex();
                        int age = param.getAge() == 0 ? (int) employee.getAge() : param.getAge();
                        String city = param.getCity() == null ? employee.getCity() : param.getCity();
                        String phoneNumber = param.getPhone() == null ? employee.getPhoneNumber() : param.getPhone();
                        String department = param.getNullableDept() == null ? employee.getDeptId() : param.getDid();
                        String email = param.getEmail() == null ? employee.getEmail() : param.getEmail();

                        adminMapper.updateEmployee(id, name, sex, age, city, phoneNumber, email, department);
                        adminMapper.addLog("update " + id + "to: "
                                + name + " " + sex + " " + age + " " + city + " " + phoneNumber
                                + " " + department + " " + email);
                        sqlSession.commit();

                        employee = adminMapper.getEmployee(id);
                        Department dept = adminMapper.getDept(employee.getDeptId());
                        sqlSession.commit();

                        System.out.println("更新之后: ");
                        PrintingTool.wrapToTable(employee, dept, true);


                        sqlSession.close();
                        System.out.println("update finished!");
                    }
                        //TODO update employee name by id
                        break;
                    case "addEmployee":{
                        String id = param.getUid();
                        String name = param.getName();
                        String did = param.getDid();
                        int age = param.getAge();
                        String sex = param.getSex();
                        String email = param.getEmail() == null ? "bth@fudan.edu.cn" : param.getEmail();
                        String city = param.getCity() == null ? "艾欧尼亚" : param.getCity();
                        String phone = param.getPhone() == null ? "1111111": param.getPhone();

                        adminMapper.addEmployee(id, name, did, age, sex, email, city, phone);
                        adminMapper.addLog("update employee information of " + id + "to: "
                                + name + " " + sex + " " + age + " " + city + " " + phone
                                + " " + did + " " + email);
                        sqlSession.commit();

                        Employee employee = adminMapper.getEmployee(id);
                        Department dept = adminMapper.getDept(employee.getDeptId());
                        sqlSession.commit();
                        System.out.println("新增了员工: ");
                        PrintingTool.wrapToTable(employee, dept, true);

                        sqlSession.close();
                        System.out.println("add finished!");
                        break;
                    }
                    //添加员工（需要参数：id, name, did）
                    case "deleteEmployee":{
                        String id = param.getUid();
                        adminMapper.deleteEmployee(id);
                        adminMapper.addLog("deleting employee " + id);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                        break;
                    }
                        //TODO 删除员工 by id
                    case "getCourse":{
                        String cid = param.getCid();
                        Course course = adminMapper.getCourse(cid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println(PrintingTool.toString(course));
                        break;
                    }
                        //TODO get course by cid
                    case "updateCourse":{
                        String cid = param.getCid();
                        String title = param.getTitle();
                        adminMapper.updateCourse(cid, title);
                        adminMapper.addLog("update course title of " + cid + ": " + title);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                        break;
                    }
                        //TODO update course title by cid
                    case "addCourse":{
                        String cid = param.getCid();
                        String title = param.getTitle();
                        String iid = param.getIid();
                        String type = param.getType();
                        adminMapper.addCourse(cid, title, iid, type);
                        adminMapper.addLog("add course " + cid + " " + title);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 添加课程(参数：cid, title, instructor)
                        break;
                    case "deleteCourse":{
                        String cid = param.getCid();
                        adminMapper.deleteCourse(cid);
                        adminMapper.addLog("delete course " + cid);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO 删除课程（参数：cid）
                        break;
                    case "getInfo":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);
                        Department dept = adminMapper.getDept(employee.getDeptId());
                        System.out.println("这位员工的信息是：");
                        PrintingTool.wrapToTable(employee, dept, true);
                        List<TestRecord> testRecords = adminMapper.getTestRecords(id);
                        sqlSession.commit();
                        sqlSession.close();
                        for (TestRecord testRecord : testRecords) {
                            System.out.println(PrintingTool.toString(testRecord));
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
                        System.out.println(PrintingTool.toString(log));
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
                    case  "addInstructor":{
                        String id = param.getUid();
                        Employee employee = adminMapper.getEmployee(id);
                        String name = employee.getName();
                        adminMapper.addInstructor(id, name);
                        sqlSession.commit();
                        sqlSession.close();
                    }
                        break;
                    case  "deleteInstructor":{
                        String id = param.getUid();
                        adminMapper.deleteInstructor(id);
                        sqlSession.commit();
                        sqlSession.close();
                    }
                        break;
                    case  "addManager":{
                        String id = param.getUid();
                        String did = param.getDid();
                        Employee employee = adminMapper.getEmployee(id);
                        String name = employee.getName();
                        adminMapper.addManager(id, name, did);
                        sqlSession.commit();
                        sqlSession.close();
                    }
                        break;
                    case  "deleteManager":{
                        String id = param.getUid();
                        adminMapper.deleteManager(id);
                        sqlSession.commit();
                        sqlSession.close();
                    }
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
