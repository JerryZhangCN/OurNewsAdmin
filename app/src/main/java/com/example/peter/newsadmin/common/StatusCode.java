package com.example.peter.newsadmin.common;

/**
 * Created by peter on 23/3/2017.
 */

public class StatusCode {
    //服务器返回成功
    public static final int RESPONSE_OK =100;
    //会议时间修改冲突
    public static final int RESPONSE_CONFLICT =202;
    //登录失败三次后账号被锁定，
    public static final  int RESPONSE_LOGINNAME_LOCKING=108;
    //会议正在进行中无法删除
    public static final  int RESPONSE_MEETING_ING_REMOVE_FAILED=206;

    public static final int SHOW_INFO_TOAST = 406;//展示信息Toast
    public static final int SHOW_INFO_DIALOG = 407;//展示信息Dialog



}
