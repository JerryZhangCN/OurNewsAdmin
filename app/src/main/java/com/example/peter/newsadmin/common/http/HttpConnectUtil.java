package com.example.peter.newsadmin.common.http;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class HttpConnectUtil {

    public static void request(String json, int apiType, StringCallback callBack) {

        switch (apiType) {
            case APIType.REQUEST_LOGIN:
                OkHttpUtils.postString().url(APIURL.LOGIN).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_REGISTER:
                OkHttpUtils.postString().url(APIURL.REGISTER).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_HOME_NEWS://获取首页新闻
                OkHttpUtils.postString().url(APIURL.GET_HOME_NEWS).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_TYPE_NEWS://获取某个类别新闻列表
                OkHttpUtils.postString().url(APIURL.GET_TYPE_NEWS).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_GET_NEWS_CONTENT:
                OkHttpUtils.postString().url(APIURL.GET_NEWS_CONTENT).mediaType(MediaType.parse("application/json; charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_GET_CODE:
                OkHttpUtils.postString().url(APIURL.GET_CODE).mediaType(MediaType.parse("charset=utf-8")).content(json).id(apiType).build().execute(callBack);
                break;
            default:
                break;
        }

    }

    public static void requestParams(Map<String, String> params, int apiType, StringCallback callBack) {
        switch (apiType) {
            case APIType.REQUEST_GET_CODE:
                OkHttpUtils.post().url(APIURL.GET_CODE).params(params).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_REGISTER:
                OkHttpUtils.post().url(APIURL.REGISTER).params(params).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_LOGIN:
                OkHttpUtils.post().url(APIURL.LOGIN).params(params).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_PUT_NEWS:
                OkHttpUtils.post().url(APIURL.PUT_NEWS).params(params).id(apiType).build().execute(callBack);
                break;
            case APIType.REQUEST_TYPE_NEWS:{
                OkHttpUtils.post().url(APIURL.GET_TYPE_NEWS).params(params).id(apiType).build().execute(callBack);
                break;
            }
            case APIType.REQUEST_GET_NEWS_CONTENT:{
                OkHttpUtils.post().url(APIURL.GET_NEWS_CONTENT).params(params).id(apiType).build().execute(callBack);
                break;
            }
            case APIType.REQUEST_UPDATE_INFO:{
                OkHttpUtils.post().url(APIURL.UPDATE_USER_INFO).params(params).id(apiType).build().execute(callBack);
                break;
            }
            case APIType.REQUEST_GET_OWN_NEWS:{
                OkHttpUtils.post().url(APIURL.GET_OWN_NEWS).params(params).id(apiType).build().execute(callBack);
                break;
            }
            case APIType.REQUEST_CHANGE_STATE:{
                OkHttpUtils.post().url(APIURL.SET_STATE).params(params).id(apiType).build().execute(callBack);
                break;
            }
            case APIType.REQUEST_PUSH_NEW:{
                OkHttpUtils.post().url(APIURL.PUSH_NEWS).params(params).id(apiType).build().execute(callBack);
                break;
            }
        }
    }
}
