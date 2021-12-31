package com.db_project.main;

public class UserInfo {
    private String uid;
    private String type;

    UserInfo(String uid, String type){
        this.uid = uid;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public String getType() {
        return type;
    }
}
