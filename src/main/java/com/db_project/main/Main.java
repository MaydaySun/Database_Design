package com.db_project.main;

import com.db_project.commands.AdminCommand;
import com.db_project.commands.EmployeeCommand;
import com.db_project.commands.InstructorCommand;
import com.db_project.commands.ManagerCommand;
import com.db_project.dao.EmployeeMapper;
import com.db_project.dao.InstructorMapper;
import com.db_project.dao.ManagerMapper;
import com.db_project.model.Employee;
import com.db_project.model.Instructor;
import com.db_project.model.Manager;
import com.db_project.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CmdLineException {
        console();
    }

    private static void console() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] arguments = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(arguments);
            if ("login".equals(arguments[0])){
                SqlSession sqlSession = MybatisUtils.getSqlSession();
                try {
                    switch (param.getType()){
                        case "admin":
                            if ("admin".equals(param.getUid())){
                                System.out.println("Admin logged in!");
                                AdminCommand adminCommand = new AdminCommand();
                                adminCommand.listen();
                            }
                            else {
                                System.out.println("wrong username for admin");
                            }
                            sqlSession.close();
                            break;
                        case "employee":
                            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
                            Employee employee = employeeMapper.getEmployeeById(param.getUid());// TODO sql执行失败是异常还是返回null
                            sqlSession.close();
                            EmployeeCommand employeeCommand = new EmployeeCommand(employee);
                            employeeCommand.listen();
                            break;
                        case "manager":
                            ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
                            Manager manager = managerMapper.getManagerByUid(param.getUid());// TODO sql执行失败是异常还是返回null
                            sqlSession.close();
                            ManagerCommand managerCommand = new ManagerCommand(manager);
                            managerCommand.listen();
                            break;
                        case "instructor":
                            InstructorMapper instructorMapper = sqlSession.getMapper(InstructorMapper.class);
                            Instructor instructor = instructorMapper.getInstructor(param.getUid());// TODO sql执行失败是异常还是返回null
                            sqlSession.close();
                            InstructorCommand instructorCommand = new InstructorCommand(instructor);
                            instructorCommand.listen();
                            break;
                    }
                }
                catch (SQLException e){
                    sqlSession.rollback();// sql执行失败，进行回滚
                    sqlSession.close();
                    System.err.println("Sql failed: " + e.getSQLState());
                    e.printStackTrace();
                }
                catch (ArgNotFoundException e){
                    System.out.println("Necessary args not found for your instruction!");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("请先登录！\r\n");
            }
        }
    }
}
