package com.example.peter.newsadmin.common.http;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class APIType {

    //1.	登录
    public static final int REQUEST_LOGIN = 1;     //
    //2.	注册
    public static final int REQUEST_REGISTER = 2;
    //3.	获取首页新闻
    public static final int REQUEST_HOME_NEWS = 3;  //
    //4.	获取对应类别新闻列表
    public static final int REQUEST_TYPE_NEWS = 4;
    //5.     获取新闻内容
    public static final int REQUEST_GET_NEWS_CONTENT= 5;  //
    //6.    上传图片
    public static final int REQUEST_PULL_IMAGE=6;
    //7.    获取验证码
    public static final int REQUEST_GET_CODE=7;
    //8.    上传新闻
    public static final int REQUEST_PUT_NEWS=8;
    //9.    修改信息
    public static final int REQUEST_UPDATE_INFO=9;
    //10.   推送新闻列表
    public static final int REQUEST_GET_OWN_NEWS=10;
}
