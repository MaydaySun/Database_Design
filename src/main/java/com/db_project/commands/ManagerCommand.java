package com.db_project.commands;

import com.db_project.main.Param;
import com.db_project.model.Manager;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Scanner;

public class ManagerCommand {
    private Manager manager;

    public ManagerCommand(Manager manager){this.manager = manager;}

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            switch (args[0]){
                case "getEmployees":
                    //TODO get employees' info by manager.dept_id(employees in department but not in manager)
                    break;
                case "getCourses":
                    //TODO get courses associated to department by manager.dept_id
                    break;
                case "distribute":
                    //TODO 分配给指定id的员工指定课程（参数：id, cid）
                    break;
                case "getTestRecords":
                    //TODO 查询指定id的员工的test record by id
                    break;
                case "shift":
                    //TODO 根据id/name转移指定员工到指定部门（参数：id/name）
                    break;
                case "getPassed":
                    //TODO 查询已通过的takes（员工&课程） by manager.dept_id
                    break;
                case "getRequired":
                    //TODO 查看必修课的所有takes（课程&员工通过情况) by manager.dept_id
                    break;
                case "getFailed":
                    //TODO 查询指定cid课程未通过的员工 by manager.dept_id
                case "getShiftable":
                    //TODO 查询可以转部门的员工（即本部门必修课、员工在本部门的选修课全部通过的员工） by manager.dept_id
                case "getNewCourses":
                    //TODO 查询新部门的必修课 by did
                case "logout":
                    return;// 登出，返回到Main.console()中等待下一次登录
            }
        }
    }
}
