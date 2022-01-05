package com.db_project.commands;

import com.db_project.dao.LogMapper;
import com.db_project.dao.ManagerMapper;
import com.db_project.main.ArgNotFoundException;
import com.db_project.main.Param;
import com.db_project.model.Course;
import com.db_project.model.Department;
import com.db_project.model.Employee;
import com.db_project.model.Manager;
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

public class ManagerCommand {
    private final Manager manager;

    public ManagerCommand(Manager manager){
        this.manager = manager;
    }

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){

            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            if(args.length != 1) {
                parser.parseArgument(Arrays.copyOfRange(args, 1, args.length));
            }

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
            LogMapper logMapper = sqlSession.getMapper(LogMapper.class);

            try {
                switch (args[0]) {
                    //查看部门的所有员工
                    case "getEmployees": {
                        List<Employee> employees = managerMapper.getEmployeesByDeptId(manager.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();
                        System.out.println("本部门有这些员工（不包括部门主管）：");
                        if (employees != null && employees.size() != 0) {
                            PrintingTool.wrapToTable(employees);
                        }
                        break;
                    }
                    //查看部门的所有课程
                    case "getCourses": {
                        List<Map<String, Object>> courses = managerMapper.getCoursesByDeptId(manager.getDeptId());
                        System.out.println("本部门下有这些课");
                        if (courses != null && courses.size() != 0) {
                            PrintingTool.printInTable(courses);
                        } else {
                            System.out.println("本部门暂时没有课，要不去找资深员工开几个呗_(:з」∠)_");
                        }
                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    //分配给员工课程
                    case "distribute": {
                        Employee employee = managerMapper.getEmployeeByIdAndDeptId(param.getUid(), manager.getDeptId());
                        Course course = managerMapper.getCourseByCid(param.getCid());

                        if (employee != null && course != null) {
                            //0是没上过， 1是上过
                            int hasTaken = managerMapper.hasTaken(employee.getId(), course.getCourseId());
                            if( hasTaken == 0 ) {
                                managerMapper.addCourseToEmployee(employee.getId(), course.getCourseId());
                                logMapper.addLog(manager.getEmployeeId(), "给员工 " + employee.getName()
                                        + "分配了课程：" + course.getTitle());
                                System.out.println("给员工 " + employee.getName() + "分配了课程：" + course.getTitle());
                            }
                            else {
                                System.out.println("该员工已经被分配了该课程");
                            }
                        }
                        else {
                            System.out.println("请检查员工id或课程id是否正确");
                        }
                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    //查看某个员工的所有考试成绩
                    case "getTestRecords": {
                        String id = param.getUid();
                        Employee employee = managerMapper.getEmployeeByIdAndDeptId(id, manager.getDeptId());
                        if (employee == null){
                            System.out.println("该员工不存在");
                        }
                        else {
                            List<Map<String, Object>> testRecords = managerMapper.getTestRecords(employee.getId());
                            sqlSession.commit();
                            sqlSession.close();

                            if(testRecords == null || testRecords.size() == 0){
                                System.out.println("该员工尚未参加任何考试");
                            }
                            else {
                                System.out.println("该员工的历史考试情况如下：");
                                PrintingTool.printInTable(testRecords);
                            }
                        }
                        break;
                    }
                    //把员工调去新部门
                    case "shift": {
                        String id = param.getUid();
                        String newDeptId = param.getDid();
                        Employee employee = managerMapper.getEmployeeByIdAndDeptId(id, manager.getDeptId());
                        if (employee != null && employee.getDeptId().equals(manager.getDeptId())) {
                            int unpassedCnt = managerMapper.getUnpassedCompulsoryCourseCnt(id, manager.getDeptId());
                            if(unpassedCnt != 0){
                                System.out.println("该员工在现部门仍有未通过或没完成的课程");
                            }else {
                                System.out.println("将 " + employee.getName() + "转走");
                                managerMapper.moveToNewDept(id, newDeptId);
                                List<String> newCompulsoryCourseID = managerMapper.getUnfinishedCompulsoryCourseID(newDeptId, id);
                                for(String courseId : newCompulsoryCourseID){
                                    managerMapper.addNewTakes(id, courseId);
                                }
                                System.out.println("转走后需要再修" + newCompulsoryCourseID.size() + "门课");
                                sqlSession.commit();
                                sqlSession.close();
                            }
                        } else {
                            if (employee != null) {
                                System.out.println("你找的id对应的是这位");
                                PrintingTool.wrapToTable(employee);
                                System.out.println("他/她不是你的部门的员工");
                            } else {
                                System.out.println("该id不存在");
                            }
                        }
                        break;
                    }
                    //查看部门所有的通过记录
                    case "getPassed": {
                        List<Map<String, Object>> courses = managerMapper.getPassedTakes(manager.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();

                        if (courses.size() != 0) {
                            System.out.println("这些人通过了这些课");
                            PrintingTool.printInTable(courses);
                        } else {
                            System.out.println("你们部门没有任何人通过了任何课");
                        }
                        break;
                    }
                    //查看某一门课所有的通过记录
                    case "getCoursePassed": {
                        String courseId = param.getCid();
                        Course course = managerMapper.getCourseByCid(courseId);
                        Department department = managerMapper.getDeptByCid(courseId);

                        if(course == null || !department.getDeptId().equals(manager.getDeptId()) ){
                            System.out.println("课程id不存在或不属于你的部门");
                        }else {
                            List<Map<String, Object>> courses = managerMapper.getEmployeeWithSituation(courseId, department.getDeptId() ,1);
                            System.out.println("这些人通过了  " + course.getTitle());
                            PrintingTool.printInTable(courses);
                        }
                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    //查看某一门课所有的不通过记录
                    case "getCourseFailed": {
                        String courseId = param.getCid();
                        Course course = managerMapper.getCourseByCid(courseId);
                        Department department = managerMapper.getDeptByCid(courseId);

                        if(course == null || !department.getDeptId().equals(manager.getDeptId()) ){
                            System.out.println("课程id不存在或不属于你的部门");
                        }else {
                            List<Map<String, Object>> courses = managerMapper.getEmployeeWithSituation(courseId, department.getDeptId() ,0);
                            System.out.println("这些人没有通过  " + course.getTitle());
                            PrintingTool.printInTable(courses);
                        }
                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    //查看某一门课的成绩
                    case "getCourseScore":{
                        String courseId = param.getCid();
                        Course course = managerMapper.getCourseByCid(courseId);
                        Department department = managerMapper.getDeptByCid(courseId);

                        if(course == null || !department.getDeptId().equals(manager.getDeptId()) ){
                            System.out.println("课程id不存在或不属于你的部门");
                        }else {
                            List<Map<String, Object>> courses = managerMapper.getCourseScores(courseId, department.getDeptId());
                            System.out.println(course.getTitle() + "的课程得分是：");
                            PrintingTool.printInTable(courses);
                        }
                        sqlSession.commit();
                        sqlSession.close();
                        break;
                    }
                    //查看部门所有上课记录
                    case "getTaken": {
                        List<Map<String, Object>> courses = managerMapper.getAllTakes(manager.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();
                        if (courses.size() != 0) {
                            for(Map<String, Object> course: courses){
                                if( (int)course.get("is_passed") == 1 ){
                                    course.put("is_passed", "通过");
                                }
                                else if( (int)course.get("is_passed") == 0 ){
                                    course.put("is_passed", "挂科");
                                }
                                else {
                                    course.put("is_passed", "未结课");
                                }
                            }
                            System.out.println("这些人上了这些课：");
                            PrintingTool.printInTable(courses);
                        } else {
                            System.out.println("你们部门没有任何人上了任何课");
                        }
                        break;
                    }
                    //查找未通过员工未通过的次数
                    case "getFailedTimes": {
                        String courseId = param.getCid();
                        Course course = managerMapper.getCourseByCid(courseId);
                        Department department = managerMapper.getDeptByCid(courseId);

                        if(course == null || !department.getDeptId().equals(manager.getDeptId()) ){
                            System.out.println("课程id不存在或不属于你的部门");
                        }else {
                            int times = param.getCount();
                            List<Map<String, Object>> employeeAndTimes = managerMapper.getFailedTimes(courseId);
                            sqlSession.commit();
                            sqlSession.close();
                            for(Map<String, Object> employee: employeeAndTimes){
                                if( (int)employee.get("count") <= times ){
                                    employeeAndTimes.remove(employee);
                                }
                            }
                            System.out.println("不通过课程 " + course.getTitle() + "至少" + times + "次的人有这些");
                            PrintingTool.printInTable(employeeAndTimes);
                        }
                        break;
                    }
                    case "getShiftable": {
                        List<Employee> employees = managerMapper.getShiftableEmployee(manager.getDeptId());
                        sqlSession.commit();
                        sqlSession.close();
                        if (employees.size() == 0) {
                            System.out.println("暂时没有可以转走的员工");
                        }
                        else{
                            System.out.println("可以转走的员工有这些：");
                            PrintingTool.wrapToTable(employees);
                        }
                            break;
                    }
                    case "getNewCourses":
                        String newDeptId = param.getDid();
                        String id = param.getUid();

                        int deptId = Integer.parseInt(newDeptId);
                        Employee employee = managerMapper.getEmployeeByIdAndDeptId(id, manager.getDeptId());
                        if( !(deptId >= 0 && deptId <= 10) || employee == null ){
                            System.out.println("无效的部门/员工编号");
                        }else {
                            System.out.println("转入新部门后，" + employee.getName() + "有以下必修课：");
                            List<Map<String, Object>> courses = managerMapper.getNewCourse(id, newDeptId);
                            PrintingTool.printInTable(courses);
                        }
                        sqlSession.commit();
                        sqlSession.close();
                    case "logout":
                        return;// 登出，返回到Main.console()中等待下一次登录
                }
            }catch (SQLException e){
                sqlSession.rollback();// sql执行失败，进行回滚
                sqlSession.close();
                System.err.println("Sql failed: " + e.getSQLState());
                e.printStackTrace();
            } catch (ArgNotFoundException e) {
                System.out.println("invalid input, recheck your args");
                e.printStackTrace();
            }
        }
    }
}
