package com.jiazhe.youxiang.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 将字符串转为date类型
     */
    public static Date strToDate(String str) throws ParseException {
        //小写的mm表示的是分钟
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(str);
    }

    /**
     * 将字符串转为date类型
     */
    public static Date strToMinutes(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.parse(str);
    }

    /**
     * 将字符串转为date类型
     */
    public static Date strToHour(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        return sdf.parse(str);
    }

    /**
     * 返回yyyyMMDDhhmmss
     */
    public static String yyyyMMDDhhmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(new Date());
        return str;
    }

    /**
     * 返回yyyyMMDDhhmmss
     */
    public static String yyyyMMDDhhmmssSSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = sdf.format(new Date());
        return str;
    }

    /**
     * 返回yyyyMMDDhh
     */
    public static String yyyyMMDDhh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String str = sdf.format(new Date());
        return str;
    }

    /**
     * 将XXXX-XX-XX XX:XX:XX的Long型转为XXXX-XX-XX 00:00:00的Long型
     */
    public static Long getFirstSecond(Long time) throws ParseException {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date) + " 00:00:00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convert = sdf1.parse(str);
        return convert.getTime();
    }

    /**
     * 将XXXX-XX-XX XX:XX:XX的Long型转为XXXX-XX-XX 23:59:59的Long型
     */
    public static Long getLastSecond(Long time) throws ParseException {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date) + " 23:59:59";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convert = sdf1.parse(str);
        return convert.getTime();
    }


}
