package com.db_project.commands;

import com.db_project.dao.EmployeeMapper;
import com.db_project.main.Param;
import com.db_project.model.Department;
import com.db_project.model.Employee;
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

public class EmployeeCommand {
    private Employee employee;

    public EmployeeCommand(Employee employee){this.employee = employee;}

    public void listen() throws CmdLineException {
        System.out.println("欢迎，" + employee.getName());
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            if(args.length > 1) {
                try {
                    parser.parseArgument(Arrays.copyOfRange(args, 1, args.length));
                }
                catch (CmdLineException e){
                    System.out.println("invalid option in your command");
                    e.printStackTrace();
                    continue;
                }

            }

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            try {
                switch (args[0]) {
                    //get employee info by employee.id
                    case "getInfo": {
                        Department department = employeeMapper.getDept(employee.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("您的个人信息");
                        PrintingTool.wrapToTable(employee, department, true);
                        break;
                    }
                    case "updateInfo": {
                        //各个信息要是写了就是新写的，没有的话就是原有的
                        String name = param.getNullableName() == null ? employee.getName() : param.getNullableName();
                        String sex = param.getSex() == null ? employee.getGender() : param.getSex();
                        int age = param.getAge() == 0 ? (int) employee.getAge() : param.getAge();
                        String city = param.getCity() == null ? employee.getCity() : param.getCity();
                        String phoneNumber = param.getPhone() == null ? employee.getPhoneNumber() : param.getPhone();
                        String email = param.getEmail() == null ? employee.getEmail() : param.getEmail();
                        employeeMapper.update(employee.getId(), name, sex, age, city, email, phoneNumber);

                        this.employee = employeeMapper.getEmployeeById(employee.getId());
                        Department department = employeeMapper.getDept(employee.getDeptId());
                        System.out.println("你现在的个人信息是：");
                        PrintingTool.wrapToTable(employee, department, true);

                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    case "getCourses": {
                        List<Map<String, Object>> courses = employeeMapper.getCourses(employee.getId());
                        sqlSession.commit();
                        sqlSession.close();

                        if(courses.size() != 0) {
                            for (Map<String, Object> course: courses) {
                                if(!course.containsKey("status")){
                                    course.put("status", -1);
                                }

                                if( (int)course.get("status") == 0){
                                    course.put("status", "挂科");
                                }
                                else if( (int)course.get("status") == 1 ) {
                                    course.put("status", "通过");
                                }
                                else{
                                    course.put("status", "未结课");
                                }
                            }
                            System.out.println("你有如下的课");
                            PrintingTool.printInTable(courses);
                        }else {
                            System.out.println("你暂时没有课");
                        }
                        break;
                    }
                    case "getTestRecords": {
                        List<Map<String, Object>> testResults = employeeMapper.getTestRecords(employee.getId());
                        sqlSession.commit();
                        sqlSession.close();
                        if(testResults.size() == 0){
                            System.out.println("你尚未参加考试");
                        }else {
                            System.out.println("你的考试记录如下：");
                            PrintingTool.printInTable(testResults);
                        }
                        break;
                    }
                    case "logout":
                        System.out.println("bye~");
                        return;// 登出，返回到Main.console()中等待下一次登录
                    default:
                        System.out.println("unknown command type");
                        break;
                }
            } catch (SQLException e) {
                sqlSession.rollback();
                sqlSession.close();
                System.err.println("Sql failed: " + e.getSQLState());
                e.printStackTrace();
            }
        }
    }
}
