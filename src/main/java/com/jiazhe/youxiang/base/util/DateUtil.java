package com.jiazhe.youxiang.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TU on 2018/9/2.
 */
public class DateUtil {

    public static Date strToyyyy_mm_dd(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return str.equals("")?null:sdf.parse(str);
    }
}
