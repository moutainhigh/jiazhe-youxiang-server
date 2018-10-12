package com.jiazhe.youxiang.base.util;

/**
 * Created by TU on 2018/8/30.
 * 字符串工具类
 */
public class StringUtil {

    //null转为空字符串
    public static String nullToEmptyStr(String str){
        if(null==str){
            return "";
        }else{
            return str;
        }
    }
}
