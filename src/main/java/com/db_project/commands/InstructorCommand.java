package com.db_project.commands;

import com.db_project.dao.InstructorMapper;
import com.db_project.main.ArgNotFoundException;
import com.db_project.main.Param;
import com.db_project.model.Instructor;
import com.db_project.model.Takes;
import com.db_project.utils.MybatisUtils;
import com.db_project.utils.Tool;
import org.apache.ibatis.session.SqlSession;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InstructorCommand {
    private Instructor instructor;

    public InstructorCommand(Instructor instructor){this.instructor = instructor;}

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);
            try {
                switch (args[0]){
                    case "getStudents":{
                        String iid = instructor.getEmployeeId();
                        List<Takes> students = instructorMapper.getStudents(iid);
                        sqlSession.commit();
                        sqlSession.close();
                        for (Takes takes : students) {
                            System.out.println(Tool.toString(takes));
                        }
                    }
                        //TODO get all takes by instructor.employee_id
                        break;
                    case "addGrade":{
                        String cid = param.getCid();
                        String id = param.getUid();
                        Long score = Long.parseLong(param.getScore());
                        instructorMapper.addGrade(cid, id, score);
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("Finished!");
                    }
                        //TODO add new test record for a student in some course by cid, id & score
                        // parse score from String to Long first
                        break;
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
