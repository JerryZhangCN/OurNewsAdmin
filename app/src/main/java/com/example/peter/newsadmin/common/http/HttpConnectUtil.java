package com.example.peter.newsadmin.common.http;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.MediaType;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class HttpConnectUtil {

    public static void request(String json, int apiType, StringCallback callBack){

        switch (apiType)
        {
            case  APIType.REQUEST_LOGIN:
                OkHttpUtils.postString().url(APIURL.LOGIN).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case  APIType.REQUEST_REGISTER:
                OkHttpUtils.postString().url(APIURL.REGISTER).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case  APIType.REQUEST_HOME_NEWS://获取首页新闻
                OkHttpUtils.postString().url(APIURL.GET_HOME_NEWS).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_TYPE_NEWS://根据会议ID查询会议  ????
                OkHttpUtils.postString().url(APIURL.GET_TYPE_NEWS).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
//            case APIType.REQUEST_GET_PIC://获取图片
//                OkHttpUtils.get().url(APIURL.GET_PIC).build().execute(callback);
//                break;
//            case APIType.REQUEST_MODIFY_MEETING_EXL_SETTING://修改会议室设置（专属）
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_MODIFY_MEETING_EXL_SEETING).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_MODIFY_MEETING_OF_SHARE://修改会议共享
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_MODIFY_MEETING_OF_SAHRE).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_CANCEL_MEETING://取消会议
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_CANCEL_MEETING).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_ALL_ROOMS:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_ALL_ROOMS).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_MEETINGS_OF_SPECIFIED_ROOM:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_MEETINGS_OF_SPECIFIED_ROOM).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_ROOM_SIZE_TYPE:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_ROOM_SIZE_TYPE).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_APPOINT_MEETING_OF_EXCLUSIVE_USER:
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_APPOString_MEETING_OF_EXCLUSIVE_USER).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_APPOINT_MEETING_OF_SHARE_USER:
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_APPOString_MEETING_OF_SHARE_USER).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_ROOMS_AND_MEETINGS_BY_DATE:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_ROOMS_AND_MEETINGS_BY_DATE).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_RECOMMOND_ROOMS:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_RECOMMOND_ROOMS).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_MODIFY_USER_INFO:
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_MODIFY_USER_INFO).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_SEED_BACK_INFO:
//                OkHttpUtils.post().url(APIURL.URL_REQUEST_SEED_BACK__INFO).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_MESSAGE_TEMPLATE://获取短信模板接口
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_MESSAGE_TEMPLATE).params(params).id(apiType).build().execute(callBack);
//                break;
//            case APIType.REQUEST_UPDATE_VERSION:
//                OkHttpUtils.get().url(APIURL.URL_REQUEST_UPDATE_VERSION).params(params).id(apiType).build().execute(callBack);
//                break;
//
            default:
                break;
        }

    }
    public static void getPic(String json, int id,BitmapCallback callBack){
        OkHttpUtils.postString().url(APIURL.GET_PIC).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(id).build().execute(callBack);

    }
}
