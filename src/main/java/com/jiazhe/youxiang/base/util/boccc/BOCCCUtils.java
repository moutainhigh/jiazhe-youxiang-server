/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tu
 * @version 1.0
 * @description BOCCC公共工具类
 * @created 2019-09-02 20:07
 */
public class BOCCCUtils {

    public static Logger logger = LoggerFactory.getLogger(BOCCCUtils.class);

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    /**
     * 获取商户id
     * 命名规则为MXXXXNNNNN
     * (XXXX为四位第三方系统名称，NNNNN为数字编码，范围为00001~99999)
     *
     * @return
     */
    public static String getMerchantId() throws Exception {
        return "M" + BOCCCConstant.MERCHANT_NAME + complete(BOCCCConstant.MERCHANT_ID, '0', true, 5);
    }


    /**
     * 生成n个字符
     *
     * @param n
     * @param c
     * @return
     */
    public static String generate(int n, char c) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * @param content   原始内容
     * @param c         用哪个字符来补充
     * @param isDigital 是否是数字，如果是数字域左补空格、其它域右补空格
     * @param n         补全至n位
     * @return
     */
    public static String complete(String content, char c, boolean isDigital, int n) throws Exception {
        if (getBytes(content) > n) {
            throw new Exception("内容超过将要补全的位数，发生错误");
        }
        if (isDigital) {
            return generate(n - getBytes(content), c) + content;
        } else {
            return content + generate(n - getBytes(content), c);
        }
    }

    /**
     * 获取内容的字节数
     *
     * @param content
     * @return
     */
    public static int getBytes(String content) {
        byte[] bytes = content.getBytes();
        return bytes.length;
    }

    /**
     * 将string写到文件中
     *
     * @param filePath
     * @param str
     */
    public static void writeStringToFile(String filePath, String str) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取昨日的日期字符串
     *
     * @return
     */
    public static String getYesterday() {
        Date date = new Date(System.currentTimeMillis() - BOCCCConstant.DAY_SEC);
        return df.format(date);
    }

    /**
     * 获取今日的日期字符串
     *
     * @return
     */
    public static String getToday() {
        Date date = new Date();
        return df.format(date);
    }

    public static String getTomorrow() {
        Date date = new Date(System.currentTimeMillis() + BOCCCConstant.DAY_SEC);
        return df.format(date);
    }


    /**
     * 生成文件尾信息
     * 每个文件尾需要填写：TLRL000000000000000，TLRL为固定值，
     * 后面的数字记录该文件的行数，如文件中有5210条数据，结尾行为TLRL000000000005210
     *
     * @param n
     * @return
     */
    public static String generateFileEndChar(int n) throws Exception {
        return "TLRL" + complete(String.valueOf(n), '0', true, 15);
    }

}
