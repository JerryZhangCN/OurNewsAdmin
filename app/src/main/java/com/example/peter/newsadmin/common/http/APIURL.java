package com.example.peter.newsadmin.common.http;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class APIURL {
    //服务器地址
    public static final String BASE_URL="http://112.74.52.72:8080/OurNews/";
    //登陆
    public static final String LOGIN=BASE_URL+"loginManager";
    //注册
    public static final String REGISTER=BASE_URL+"registerManager";
    //首页新闻地址
    public static final String GET_HOME_NEWS=BASE_URL+"getHomeNews";
    //对应类别新闻
    public static final String GET_TYPE_NEWS=BASE_URL+"getNewList";
    //下载图片
    public static final String GET_PIC=BASE_URL+"downloadImage";
    //获取新闻详情
    public static final String GET_NEWS_CONTENT=BASE_URL+"getNewContent";
    //上传图片
    public static final String PULL_PIC=BASE_URL+"uploadImage";
    //获取验证码
    public static final String GET_CODE=BASE_URL+"getCode";
    //上传新闻
    public static final String PUT_NEWS=BASE_URL+"addNews";
}
