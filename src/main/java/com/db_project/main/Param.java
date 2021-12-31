package com.db_project.main;

import org.kohsuke.args4j.Option;

public class Param {
    @Option(name="-id", usage = "员工id")
    private String uid;

    @Option(name="-type", usage = "用户类型")
    private String type;

    @Option(name="-name", usage = "员工名字")
    private String name;

    @Option(name="-cid", usage = "课程编号")
    private String cid;

    @Option(name="-did", usage = "部门编号")
    private String did;

    @Option(name="-iid", usage = "教员id")
    private String iid;

    @Option(name="-score", usage = "培训成绩")
    private String score;

    @Option(name="-lid", usage = "日志id")
    private String lid;

    @Option(name="-content", usage = "日志内容")
    private String content;

    public String getUid() {
        return uid;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }

    public String getDid() {
        return did;
    }

    public String getIid() {
        return iid;
    }

    public String getScore() {
        return score;
    }

    public String getLid() {
        return lid;
    }

    public String getContent() {
        return content;
    }
}
