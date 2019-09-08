package com.jiazhe.youxiang.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

   private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 将字符串转为date类型
     */
    public static Date strToDate(String str)  {
        //小写的mm表示的是分钟
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date =  sdf.parse(str);
            return date;
        } catch (ParseException e) {
            logger.error("string转换yyyy-MM-dd错误");
        }
        return null;
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  sdf.format(date);
    }

    /**
     * 将字符串转为date类型
     */
    public static Date strToMinutes(String str)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date =  sdf.parse(str);
            return date;
        } catch (ParseException e) {
            logger.error("string转换yyyy-MM-dd HH:mm错误");
        }
        return null;
    }

    /**
     * 将字符串转为date类型
     */
    public static Date strToHour(String str)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = null;
        try {
            date =  sdf.parse(str);
            return date;
        } catch (ParseException e) {
            logger.error("string转换yyyy-MM-dd HH错误");
        }
        return null;
    }

    /**
     * 将字符串转为date类型
     */
    public static Date yyyyMMddHHmmssStrToSeconds(String str)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date =  sdf.parse(str);
            return date;
        } catch (ParseException e) {
            logger.error("string转换Date错误");
        }
        return null;
    }

    /**
     * 返回yyyyMMDDhhmmss
     */
    public static String yyyyMMDDhhmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
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
     * 返回yyyyMMDDhh
     */
    public static String yyyyMMDD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 将XXXX-XX-XX XX:XX:XX的Long型转为XXXX-XX-XX 00:00:00的Long型
     */
    public static Long getFirstSecond(Long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date) + " 00:00:00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convert = null;
        try {
            convert = sdf1.parse(str);
        } catch (ParseException e) {
            logger.error("时间转换为XXXX-XX-XX 00:00:00的Long型错误");
        }
        return convert.getTime();
    }

    /**
     * 将XXXX-XX-XX XX:XX:XX的Long型转为XXXX-XX-XX 23:59:59的Long型
     */
    public static Long getLastSecond(Long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date) + " 23:59:59";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convert = null;
        try {
            convert = sdf1.parse(str);
        } catch (ParseException e) {
            logger.error("时间转换为XXXX-XX-XX 23:59:59的Long型错误");
        }
        return convert.getTime();
    }
}
