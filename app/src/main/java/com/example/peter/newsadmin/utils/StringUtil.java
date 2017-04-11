package com.example.peter.newsadmin.utils;

import android.support.annotation.NonNull;

import com.example.peter.newsadmin.common.http.APIURL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 22/3/2017.
 * String工具类
 */

public class StringUtil {
    private final String DIFFERENCE_STRING = "AozakiShiki";

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

    private List<String> getPhotoString(String str) {
        List<String> list = null;
        while (true) {
            if (str.contains("<json>") && str.contains("</json>")) {
                if (list == null)
                    list = new ArrayList<>();
                int length = str.indexOf("<json>");
                if (length != 0) {
                    list.add(str.substring(0, str.indexOf("<json>") - 1));
                }
                list.add(DIFFERENCE_STRING + str.substring(str.indexOf("<json>") + 6, str.indexOf("</json>")));
                str = str.substring(str.indexOf("</json>") + 7, str.length());
            } else {
                if (list != null) {
                    list.add(str);
                }
                break;
            }
        }
        return list;
    }
}
