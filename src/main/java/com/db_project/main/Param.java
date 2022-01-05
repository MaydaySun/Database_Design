package com.db_project.main;

import org.kohsuke.args4j.Option;

public class Param {
    @Option(name="-id", usage = "员工id")
    private String uid;

    @Option(name="-type", usage = "用户类型")
    private String type;

    @Option(name="-name", usage = "员工名字")
    private String name;

    @Option(name = "-phone", usage = "电话")
    private String phone;

    @Option(name = "-sex", usage = "性别")
    private String sex;

    @Option(name = "-city", usage = "城市")
    private String city;

    @Option(name = "-mail", usage = "email")
    private String email;

    @Option(name = "-age", usage = "年龄")
    private int age;

    @Option(name="-cid", usage = "课程编号")
    private String cid;

    @Option(name = "content")
    private String courseContent;

    @Option(name="-title", usage = "课程名称")
    private String title;

    @Option(name="-did", usage = "部门编号")
    private String did;

    @Option(name="-iid", usage = "教员id")
    private String iid;

    @Option(name="-required", usage = "是否必修")
    private String required;

    @Option(name="-score", usage = "培训成绩")
    private String score;

    @Option(name="-lid", usage = "日志id")
    private String lid;

    @Option(name="-content", usage = "日志内容")
    private String content;

    @Option(name = "-count", usage = "次数")
    private int count;

    public String getUid() throws ArgNotFoundException {
        if (uid == null)
            throw new ArgNotFoundException();
        return uid;
    }

    public String getType() throws ArgNotFoundException {
        if (type == null)
            throw new ArgNotFoundException();
        return type;
    }

    public String getName() throws ArgNotFoundException {
        if (name == null)
            throw new ArgNotFoundException();
        return name;
    }

    public String getCid() throws ArgNotFoundException {
        if (cid == null)
            throw new ArgNotFoundException();
        return cid;
    }

    public String getTitle() throws ArgNotFoundException {
        if (title == null)
            throw new ArgNotFoundException();
        return title;
    }

    public String getDid() throws ArgNotFoundException {
        if (did == null)
            throw new ArgNotFoundException();
        return did;
    }

    public String getIid() throws ArgNotFoundException {
        if (iid == null)
            throw new ArgNotFoundException();
        return iid;
    }

    public String getRequired() throws ArgNotFoundException{
        if (required == null)
            throw new ArgNotFoundException();
        return required;
    }

    public String getScore() throws ArgNotFoundException {
        if (score == null)
            throw new ArgNotFoundException();
        return score;
    }

    public String getLid() throws ArgNotFoundException {
        if (lid == null)
            throw new ArgNotFoundException();
        return lid;
    }

    public String getContent() throws ArgNotFoundException {
        if (content == null)
            throw new ArgNotFoundException();
        return content;
    }

    public String getCourseContent() throws ArgNotFoundException{
        if (courseContent == null)
            return "本课程暂无内容";
        return courseContent;
    }

    //nullable part

    public String getPhone(){
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public String getCity(){
        return city;
    }

    public String getEmail(){
        return email;
    }

    public String getNullableName(){
        return this.name;
    }

    public int getAge(){
        return age;
    }

    public String getNullableDept(){
        return did;
    }

    public int getCount(){
        return count;
    }


}
