package com.example.peter.newsadmin.base;

import com.example.peter.newsadmin.utils.StringUtil;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class BasePresenter {
    protected static final String TAG = BasePresenter.class.getName();

//    protected synchronized Map<String,String> getRequestBasicInfo(){
//
//        String timeStamp = DateUtil.getTimeStamp();
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("loginName", UserCache.getInstance().getLoginName());
//        params.put("token", getToken(timeStamp));
//        params.put("timeStamp", timeStamp);
//        return params;
//    }
    /**
     * 获取向服务器传递的token
     * 用户登录名+“|”+用户加密后密码+ “|”+ timeStamp组成字符串后使用MD5加密方式（一次有效，再次请求需重新生成）
     * @return
     */
//    protected synchronized String getToken(String timeStamp){
//
//        return com.prj.sdk.algo.MD5Tool.getMD5(UserCache.getInstance().getLoginName()+"|"
//                +UserCache.getInstance().getLoginPassword()+"|"
//                + timeStamp);
//    }

    /**
     * 返回response是否成功
     * @param result
     * @param
     * @return
     */
    protected  boolean isSuccess(String result){
        return !StringUtil.isEmpty(result) && result.equals("success");
    }

}
