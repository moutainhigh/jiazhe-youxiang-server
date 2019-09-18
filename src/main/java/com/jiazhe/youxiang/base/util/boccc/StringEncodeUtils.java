/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author tu
 * @version 1.0
 * @description 判断字符串属于哪种编码
 * @created 2019-09-18 19:48
 */
public class StringEncodeUtils {


    public static String getEncoding(String str) {
        String encode;

        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }


        return "未识别编码格式";
    }


    public static void main(String[] args) throws Exception{
        //获取系统默认编码
        System.out.println("系统默认编码：" + System.getProperty("file.encoding")); //查询结果GBK
        //系统默认字符编码
        System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果GBK
        //操作系统用户使用的语言
        System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh

        String s1 = "hi, nice to meet you!";
        String s2 = "hi, 我来了！";

        System.out.println(getEncoding(s1));
        System.out.println(getEncoding(s2));

        String gbk= URLEncoder.encode(s2,"GBK");
        System.out.println(getEncoding(gbk));

//        String utf8 = new String(s2.getBytes( "GBK"),"GBK");
//        System.out.println(getEncoding(utf8));
//        String utf8 = new String(s2.getBytes( "UTF-8"));
//        System.out.println(getEncoding(utf8));
//        String unicode = new String(utf8.getBytes(),"UTF-8");
//        System.out.println(getEncoding(unicode));
//        String gbk = new String(unicode.getBytes("GBK"));
//        System.out.println(getEncoding(gbk));
    }
}
