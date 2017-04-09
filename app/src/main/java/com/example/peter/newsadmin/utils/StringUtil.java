package com.example.peter.newsadmin.utils;

import android.support.annotation.NonNull;

import com.example.peter.newsadmin.common.http.APIURL;

/**
 * Created by peter on 22/3/2017.
 * String工具类
 */

public class StringUtil {


    public static boolean isEmpty(String str) {
        if (str == null || str.equals("") || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static String getPhotoUrl(@NonNull String photoName) {
        return APIURL.BASE_URL + "downloadImage?name=" + photoName;
    }
}
