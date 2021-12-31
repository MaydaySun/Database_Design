package com.db_project.commands;

import com.db_project.main.Param;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Scanner;

public class InstructorCommand {
    private Instructor instructor;

    InstructorCommand(Instructor instructor){this.instructor = instructor;}

    public void listen() throws CmdLineException {
        Scanner scanner = new Scanner(System.in);
        Param param = new Param();
        while (scanner.hasNextLine()){
            String[] args = scanner.nextLine().split(" ");
            CmdLineParser parser = new CmdLineParser(param);
            parser.parseArgument(args);
            switch (args[0]){
                case "getStudents":
                    //TODO get all students id & name by instructor.employee_id
                    break;
                case "addGrade":
                    //TODO add new test record for a student in some course by id, cid & score
                    break;
                case "logout":
                    return;// 登出，返回到Main.console()中等待下一次登录
            }
        }
    }
}
