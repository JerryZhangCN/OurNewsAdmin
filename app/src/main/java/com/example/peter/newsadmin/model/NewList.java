package com.example.peter.newsadmin.model;

/**
 * Created by cdxy_ on 2017/5/1.
 */

public class NewList {
    private int type;
    private int page;
    private int size;
    private int sort;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
