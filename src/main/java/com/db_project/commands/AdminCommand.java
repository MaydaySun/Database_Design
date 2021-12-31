package com.db_project.commands;

import com.db_project.main.Param;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Scanner;

public class AdminCommand {

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            switch (args[0]){
                case "getEmployee":
                    //TODO get employee's info by id
                    break;
                case "updateEmployee":
                    //TODO update employee name by id
                    break;
                case "addEmployee":
                    //TODO 添加员工（需要参数：id, name, did）
                    break;
                case "deleteEmployee":
                    //TODO 删除员工 by id
                case "getCourse":
                    //TODO get course by cid
                    break;
                case "updateCourse":
                    //TODO update course title by cid
                    break;
                case "addCourse":
                    //TODO 添加课程(参数：cid, title, instructor)
                    break;
                case "deleteCourse":
                    //TODO 删除课程（参数：cid）
                    break;
                case "getInfo":
                    //TODO 获取员工的个人信息和test record（参数：id）
                    // 分别调用getEmployeeById和getTestRecordById来获取相关信息
                    break;
                case "getLog":
                    //TODO get a single log by lid
                    break;
                case "addLog":
                    //TODO 添加log（参数：content）
                    break;
                case "logout":
                    return;// 登出，返回到Main.console()中等待下一次登录
            }
        }
    }
}
