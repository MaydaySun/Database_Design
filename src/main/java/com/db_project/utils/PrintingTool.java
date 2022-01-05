package com.db_project.utils;

import com.db_project.model.*;

import java.util.*;

//TODO write wrap
public class PrintingTool {

    public static void wrapToTable(List<Employee> employees){
        int[] lengths = {11, 8, 3, 3, 20, 4, 13, 30};
        System.out.println(getTableLine(lengths));
        System.out.println("| employeeID    | name     | sex | age |  date employed      | city | phone         " +
                "| email                        |");
        System.out.println(getTableLine(lengths));

        String id;
        String name;
        String sex;
        String age;
        String date;
        String city;
        String phone;
        String email;

        for(Employee employee: employees){
            id = wrapString(employee.getId(), 11);
            name = wrapString(employee.getName(), 8);
            sex = wrapString(employee.getGender(), 1);
            age = wrapString(employee.getAge()+"", 1);
            date = wrapString(employee.getDateEmployed().toString(), 20);
            city = wrapString(employee.getCity(), 4);
            phone = wrapString(employee.getPhoneNumber(), 13);
            email = wrapString(employee.getEmail(), 30);
            System.out.println( id + name + sex + age + date + city + phone + email + "|" );
            System.out.println(getTableLine(lengths));
        }
    }

    public static void wrapToTable(Employee employee){
        int[] lengths = {11, 8, 1, 25, 5};

        printEmployeeHeader(lengths);

        String id = wrapString(employee.getId(), 11);
        String name = wrapString(employee.getName(), 8);
        String sex = wrapString(employee.getGender(), 1);
        String date = wrapString(employee.getDateEmployed().toString(), 20);
        String dept = wrapString(employee.getDeptId(), 5);
        System.out.println( id + name + sex + date + dept + "|" );

        System.out.println(getTableLine(lengths));
    }

    public static void wrapToTable(Employee employee, Department department, boolean needsHeader){
        int[] lengths = {11, 8, 1, 1, 25, 3, 13, 30, 12};

        if(needsHeader) {
            printEmployeeHeader(lengths);
        }
        String id = wrapString(employee.getId(), 11);
        String name = wrapString(employee.getName(), 8);
        String sex = wrapString(employee.getGender(), 1);
        String age = wrapString(employee.getAge()+"", 1);
        String date = wrapString(employee.getDateEmployed().toString(), 20);
        String city = wrapString(employee.getCity(), 4);
        String phone = wrapString(employee.getPhoneNumber(), 13);
        String email = wrapString(employee.getEmail(), 30);
        String dept = wrapString(department.getDeptName(), 12);
        System.out.println( id + name + sex + age + date + city + phone + email + dept + "|" );

        System.out.println(getTableLine(lengths));
    }

    private static void printEmployeeHeader(int[] lengths){
        System.out.println(getTableLine(lengths));
        System.out.println("| employeeID  | name     |sex|age|   date employed      | city | phone         " +
                "| email                          | department   |");
        System.out.println(getTableLine(lengths));
    }


    public static String toString(Takes takes){
        return takes.getCourseId() + ", " + takes.getEmployeeId();
    }

    public static String toString(TestRecord testRecord){
        return testRecord.getCourseId() + ", " + testRecord.getEmployeeId() + ", "
                + testRecord.getScore();
    }

    public static String toString(Log log){
        return log.getLogId() + ", " + log.getUsername() + ", " + log.getContent() + ", "
                + log.getTime();
    }

    public static String toString(Course course) {
        return course.getCourseId() + ", " + course.getTitle() + ", " + course.getType();
    }

    public static void printInTable(List<Map<String, Object>> maps){
        Map<String, Object> firstObj = maps.get(0);
        HashMap<String, Integer> lengthOfValues = new HashMap<>();
        ArrayList<Integer> lengths = new ArrayList<>();

        int length;
        for( String key: firstObj.keySet() ) {
            length = ((String) firstObj.get(key)).length();
            if(length > 10) {
                lengthOfValues.put(key, length + 5);
                lengths.add(length + 5);
            }
            else {
                lengthOfValues.put(key, 2 * length);
                lengths.add(length * 2);
            }
        }
        //打印表格头
        String tableLine = getTableLine((lengths.toArray(new Integer[0])));
        System.out.println(tableLine);
        for(String key : lengthOfValues.keySet()){
            System.out.print(wrapString(key, lengthOfValues.get(key)));
        }
        System.out.println(" |");
        System.out.println(tableLine);

        //打印内容
        for (Map<String, Object> crtObj : maps){
            for( String key : crtObj.keySet() ){
                System.out.print(wrapString((String) crtObj.get(key), lengthOfValues.get(key)));
            }
            System.out.println(" |");
            System.out.println(tableLine);
        }
    }


    ///len不包括杠
    private static String wrapString(String originalString, int len){
        if (originalString == null) {
            originalString = "?";
        }

        int blankCnt = len - originalString.length();
        originalString = "| "+ originalString;
        StringBuilder sb = new StringBuilder(originalString);
        for(int i = 0; i < blankCnt; i++){
            sb.append(" ");
        }
        sb.append(" ");
        return sb.toString();
    }

    private static String getTableLine(int[] lengths){
        StringBuilder sb = new StringBuilder("+");
        for (int length : lengths) {
            for (int j = 0; j < length + 2; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        return sb.toString();
    }

    private static String getTableLine(Integer[] lengths){
        StringBuilder sb = new StringBuilder("+");
        for (int length : lengths) {
            for (int j = 0; j < length + 2; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        return sb.toString();
    }

}
