package com.jiazhe.youxiang.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TU on 2018/9/13.
 * 生成各种编码的工具类
 */
public class GenerateCode {

    /*公司编号 为CUYYMMDDNNNNN CU为固定，YY为年份的后两位，MM为月份，DD为日，NNNNN为00001，当累加*/
    public static String generateCooperationCompanyNo(int count){
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String no = "CU"+formatter.format(new Date())+String.format("%05d", count);
        return no ;
    }
}
