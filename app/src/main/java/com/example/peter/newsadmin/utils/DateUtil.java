/**
 * 用一句话描述该文件做什么.
 *
 * @title DateUtil.java
 * @package com.sinsoft.android.util
 * @author shimiso
 * @update 2012-6-26 上午9:57:56
 */
package com.example.peter.newsadmin.utils;

import android.annotation.TargetApi;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期操作工具类.
 *
 * @author shimiso
 */

public class DateUtil {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_SHORT = "MM.dd";
    public static final String FORMAT_DATE_HOUR = "HH:mm";
    public static final String FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_ONLY_MONTH = "MM";
    public static final String FORMAT_ONLY_DAY = "dd";
    public static final String FORMAT_ONLY_YAER = "yyyy";


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_DEFAULT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_DEFAULT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurTimeStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + " "
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
                + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @return
     */
    public static String getTodayStr() {
        Calendar c = Calendar.getInstance();
        return date2Str(c, FORMAT_DATE);
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    //获取当前的时间格式并到小时
    public static Calendar getDefaultTimeCalendar() {
        Calendar calendarDate = Calendar.getInstance();
        if (calendarDate.get(Calendar.MINUTE) < 30) {
            calendarDate.set(Calendar.MINUTE, 30);
        } else {
            calendarDate.add(Calendar.HOUR, 1);
            calendarDate.set(Calendar.MINUTE, 00);
        }
        return calendarDate;
    }

    //返回timeStamp
    public static String getTimeStamp() {

        String timestamp = String.valueOf(new Date().getTime());
        return timestamp;
    }

    public static String getSpecifiedDayZeroTimeStr(String startTimeStr, int offsetToday) {
        return getSpecifiedDayZeroTimeStr(startTimeStr, offsetToday, null);
    }

    public static String getSpecifiedDayZeroTimeStr(String startDateStr, int offsetToday, String format) {
        Calendar currentDate = Calendar.getInstance();

        if (!StringUtil.isEmpty(startDateStr))
            currentDate.setTime(str2Date(startDateStr, FORMAT_DATE));

        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DATE, currentDate.get(Calendar.DATE) + offsetToday);
        if (!StringUtil.isEmpty(format))
            return date2Str(currentDate, format);
        else
            return date2Str(currentDate);
    }

    /**
     * 判断是否为今天,传入yyyy-MM-dd格式
     *
     * @param time
     * @return
     */
    public static boolean isStrToday(String time) {

        if (StringUtil.isEmpty(time))
            return false;

        return time.startsWith(DateUtil.date2Str(new Date(), "yyyy-MM-dd"));
    }

    public static int todayOffset(String time) {

        Calendar calendarParam = DateUtil.str2Calendar(time, DateUtil.FORMAT_DATE);
        Calendar calendar = Calendar.getInstance();
        if (calendarParam.get(Calendar.YEAR) - calendar.get(Calendar.YEAR) > 0) {
            return 3;
        }
        return calendarParam.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static String getFormatDate(String time) {
        return getFormatDate(time, FORMAT_DATE);
    }

    public static String getFormatDate(String time, String format) {
        return getFormatDate(time, format, FORMAT_DEFAULT);
    }

    public static String getFormatDate(String time, String format, String inputFormat) {
        if (StringUtil.isEmpty(time))
            return null;
        return new SimpleDateFormat(format).format(str2Date(time, inputFormat));
    }

    /**
     * 返回毫秒数
     *
     * @return
     */
    public static final int MILLION_TYPE = 1;
    public static final int HOUR_TYPE = 2;

    public static int curTimeZoneOffset(int type, String timezoneId) {

        TimeZone tz = null;

        if (StringUtil.isEmpty(timezoneId))
            tz = TimeZone.getDefault();
        else
            tz = TimeZone.getTimeZone(timezoneId);

        int curOffest = tz.getRawOffset();
        if (tz.inDaylightTime(new Date()))
            curOffest = curOffest + tz.getDSTSavings();
        if (type == MILLION_TYPE)
            return curOffest;
        else
            return curOffest / 1000 / 3600;
    }


    /**
     * 返回标准GMT时间
     *
     * @param startTime
     * @return
     */
    public static String convert2GMTTime(String startTime) {
        return convert2GMTTime(startTime, null);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String convert2GMTTime(String startTime, String timeZoneId) {
        Calendar calendar = str2Calendar(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - curTimeZoneOffset(HOUR_TYPE, timeZoneId));
        return date2Str(calendar);
    }









        }
