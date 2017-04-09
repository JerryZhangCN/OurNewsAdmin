package com.example.peter.newsadmin.model;

/**
 * Created by cdxy_ on 2017/4/9.
 */

public class TypeNewsMode {
    private int type;
    private NewsModel[] list;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public NewsModel[] getList() {
        return list;
    }

    public void setList(NewsModel[] list) {
        this.list = list;
    }
}
