package com.example.peter.newsadmin.model;

/**
 * Created by cdxy_ on 2017/4/9.
 */

public class RequestModel {
    private String name;
    private int type;
    private long nid;//新闻Id
    private long uid;//用户id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getNid() {
        return nid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
