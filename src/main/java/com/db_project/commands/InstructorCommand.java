package com.db_project.commands;

import com.db_project.dao.InstructorMapper;
import com.db_project.dao.LogMapper;
import com.db_project.main.ArgNotFoundException;
import com.db_project.main.Param;
import com.db_project.model.Course;
import com.db_project.model.Employee;
import com.db_project.model.Instructor;
import com.db_project.model.Takes;
import com.db_project.utils.MybatisUtils;
import com.db_project.utils.PrintingTool;
import org.apache.ibatis.session.SqlSession;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InstructorCommand {
    private final Instructor instructor;

    public InstructorCommand(Instructor instructor){this.instructor = instructor;}

    public void listen() throws CmdLineException {

        System.out.println( instructor.getName() + "老师 你好");
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            try {
                parser.parseArgument(Arrays.copyOfRange( args, 1 , args.length ));
            }
            catch (CmdLineException e){
                System.out.println("invalid arg format");
                e.printStackTrace();
            }

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);
            LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
            try {
                switch (args[0]){
                    case "getStudents":{
                        String iid = instructor.getEmployeeId();
                        List<Map<String, Object>> students = instructorMapper.getStudents(iid);
                        sqlSession.commit();
                        sqlSession.close();
                        PrintingTool.printInTable(students);
                    }
                        //TODO get all takes by instructor.employee_id,
                        break;
                    case "addGrade":{
                        String cid = param.getCid();
                        String id = param.getUid();
                        long score = Long.parseLong(param.getScore());
                        Course course = instructorMapper.getCourse(cid);// 确保course是自己任教的
                        Takes takes = instructorMapper.getStudent(id, cid);// 确保学生选了这门课
                        instructorMapper.addTestRecord(cid, id, score);
                        logMapper.addLog(instructor.getEmployeeId(), "add grade " + cid + " "
                                + id + " " + score);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO add new test record for a student in some course by cid, id & score
                        // parse score from String to Long first
                        break;
                    case "associate":{
                        String cid = param.getCid();
                        String did = param.getDid();
                        String required = "1".equals(param.getRequired()) ? "必修" : "选修";
                        instructorMapper.associateCourse(cid, did, required);
                        if ("必修".equals(required)){
                            List<Employee> employees = instructorMapper.getEmployeesByDid(did);
                            for (Employee employee : employees) {
                                instructorMapper.addTakes(employee.getId(), cid);
                            }// 如果是必修关联操作，为该部门所有员工自动分配课程
                        }
                        logMapper.addLog(instructor.getEmployeeId(), "associate course and department "
                                + cid + " " + did + " " + required);

                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO associate course with department, meaning visible to the department
                    case "logout":
                        return;// 登出，返回到Main.console()中等待下一次登录
                }
            }
            catch (SQLException e){
                sqlSession.rollback();
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
