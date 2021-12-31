package com.db_project.commands;

import com.db_project.main.Param;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Scanner;

public class EmployeeCommand {
    private Employee employee;

    public EmployeeCommand(Employee employee){this.employee = employee;}

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            switch (args[0]){
                case "getInfo":
                    //TODO get employee info by employee.id
                    break;
                case "updateInfo":
                    //TODO update employee name by employee.id & name
                    break;
                case "getCourses":
                    //TODO 展示自己上的课程与教员姓名 by employee.id
                    break;
                case "getTestRecords":
                    //TODO 历史考试信息 by employee.id
                    break;
                case "logout":
                    return;// 登出，返回到Main.console()中等待下一次登录
            }
        }
    }
}
