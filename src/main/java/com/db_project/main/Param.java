package com.db_project.main;

import org.kohsuke.args4j.CmdLineException;
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
}
