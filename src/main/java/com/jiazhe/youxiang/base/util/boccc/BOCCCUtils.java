/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.HttpUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author tu
 * @version 1.0
 * @description BOCCC公共工具类
 * @created 2019-09-02 20:07
 */
@Component
public class BOCCCUtils {

    public static Logger logger = LoggerFactory.getLogger(BOCCCUtils.class);

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    /**
     * 每次重试间隔
     */
    private static Integer[] RETRY_INTERVAL = {15, 15, 30, 180, 600, 1200, 1800, 1800, 1800, 3600, 10800, 10800, 10800, 21600, 21600};

    /**
     * 中行信用卡解密字符串
     */
    public static String PASSPHRASE;

    @Value("${boccc.pgp.passphrase}")
    public void setPassPhrase(String passphrase) {
        PASSPHRASE = passphrase;
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
     * 补全内容，如果内容超过，则截取
     *
     * @param content   原始内容
     * @param c         用哪个字符来补充
     * @param isDigital 是否是数字，如果是数字域左补空格、其它域右补空格
     * @param n         补全至n位
     * @return
     */
    public static String complete(String content, char c, boolean isDigital, int n) {
        if (getBytes(content) > n) {
            return split(content, n);
        } else if (getBytes(content) == n) {
            return content;
        } else {
            if (isDigital) {
                return generate(n - getBytes(content), c) + content;
            } else {
                return content + generate(n - getBytes(content), c);
            }
        }
    }

    /**
     * 截取 n个字符
     *
     * @param content
     * @param n
     * @return
     */
    public static String split(String content, int n) {
        try {
            byte[] source = content.getBytes(BOCCCConstant.CHAR_SET);
            byte[] dest = new byte[n];
            System.arraycopy(source, 0, dest, 0, n);
            return new String(dest, BOCCCConstant.CHAR_SET);
        } catch (Exception e) {
            logger.error("字符串截取异常，原因：" + e.getMessage());
        }
        return "";
    }

    /**
     * 获取内容的字节数
     *
     * @param content
     * @return
     */
    public static int getBytes(String content) {
        try {
            byte[] bytes = content.getBytes(BOCCCConstant.CHAR_SET);
            return bytes.length;
        } catch (Exception e) {
            logger.error("获取字符串字节数异常，原因：" + e.getMessage());
        }
        return 0;
    }

    /**
     * 将string写到文件中
     *
     * @param filePath
     * @param str
     */
    public static void writeStringToFile(String filePath, String str) {
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            fos.write(str.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            FileUtils.writeStringToFile(new File(filePath), str, BOCCCConstant.CHAR_SET);
        } catch (Exception e) {
            logger.error("写入文件错误");
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
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return sdf.format(new Date());
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
     * 追加文件：使用FileWriter
     *
     * @param fileName 不存在时可以自动新建文件
     * @param content
     */
    public static void contentAppend(String fileName, String content) {
        FileWriter writer = null;
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将pgp文件copy至今日待上传的文件夹下
     *
     * @param pgpFileName
     * @throws Exception
     */
    public static void copyToUpload(String pgpFileName) throws Exception {
        String uploadPath = BOCCCConstant.uploadPath + BOCCCUtils.getToday();
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyFileToDirectory(new File(pgpFileName), new File(uploadPath));
    }

    /**
     * 判断路径是否存在，不存在则生成
     *
     * @param path
     */
    public static void mkDirs(String path) {
        File usedPath = new File(path);
        if (!usedPath.exists()) {
            usedPath.mkdirs();
        }
    }

    /**
     * 判断是不是最后一行
     *
     * @param line
     * @return
     */
    public static boolean isLastLine(String line) {
        String pattern = "TLRL\\d{15}";
        return Pattern.matches(pattern, line);
    }

    public static String UTF82GBK(String str) throws Exception {
        return new String(str.getBytes("utf8"), "GBK");
    }

    /**
     * @param url
     * @param map
     * @param current 当前重试次数 0为第一次，1为重试的第一次
     * @param total   重试次数（最大重试次数为10次，第一次current=0的那一次不计入）
     * @throws Exception
     */
    public static void httpPost(String url, Map map, Integer current, Integer total) throws Exception {
        logger.info("httpPost共重试" + total + "次，当前执行第：" + current + "次。");
        String result = HttpUtil.httpPost(url, map);
        if ("".equals(result) && current < total) {
            logger.info("httpPost：第" + current + "次重试完成，下次重试时间等待（秒）：" + RETRY_INTERVAL[current]);
            Thread.sleep(1000L * RETRY_INTERVAL[current]);
            httpPost(url, map, ++current, total);
        } else {
            if ("".equals(result)) {
                logger.info(url + map.toString() + "：最终执行失败！");
            }
            logger.info("执行完成");
        }
    }
}
