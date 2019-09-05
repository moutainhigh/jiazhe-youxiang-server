/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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

    /**
     * 获取明日的日期字符串
     *
     * @return
     */
    public static String getTomorrow() {
        Date date = new Date(System.currentTimeMillis() + BOCCCConstant.DAY_SEC);
        return df.format(date);
    }

    /**
     * 根据文件名模板生成文件名
     *
     * @param name 文件名模板
     * @param day  -1表示昨日文件  0表示今日文件   1表示明日文件
     * @return
     */
    public static String getFileName(String name, int day) {
        String result = "";
        switch (day) {
            case -1:
                result = name.replace("XXXX", BOCCCConstant.MERCHANT_NAME).replace("YYYYMMDD", getYesterday());
                break;
            case 0:
                result = name.replace("XXXX", BOCCCConstant.MERCHANT_NAME).replace("YYYYMMDD", getToday());
                break;
            case 1:
                result = name.replace("XXXX", BOCCCConstant.MERCHANT_NAME).replace("YYYYMMDD", getTomorrow());
                break;
        }
        return result;
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

    /**
     * 读取文件最后一行
     *
     * @param file
     * @param charset
     * @return
     * @throws IOException
     */
    public static String readLastLine(File file, String charset) throws IOException {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                if (charset == null) {
                    return new String(bytes);
                } else {
                    return new String(bytes, charset);
                }
            }
        } catch (FileNotFoundException e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }

}
