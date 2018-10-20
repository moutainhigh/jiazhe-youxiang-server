package com.jiazhe.youxiang.base.util;

/**
 * Created by tujia on 2018/10/11.
 */

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    public static boolean telValidate(String tel) {//电话号码验证
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (tel.length() > 9) {
            m = p1.matcher(tel);
            b = m.matches();
        } else {
            m = p2.matcher(tel);
            b = m.matches();
        }
        return b;
    }

    public static boolean phoneValidate(String phone) {//11位手机号验证
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(phone);
        b = m.matches();
        return b;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isDecimal(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?$");
        return pattern.matcher(str).matches();
    }

}
