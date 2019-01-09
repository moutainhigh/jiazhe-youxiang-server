/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http相关工具类
 *
 * @author niexiao
 * @created 2019/1/9
 */
public final class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String httpGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        StringBuilder entityStringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        CloseableHttpResponse response = null;
        JSONObject resultJsonObject = null;
        try {
            response = httpclient.execute(httpGet);
            //建立的http连接，仍旧被response保持着，允许我们从网络socket中获取返回的数据
            //为了释放资源，我们必须手动消耗掉response或者取消连接（使用CloseableHttpResponse类的close方法）
            //得到httpResponse的实体数据
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                try {
                    bufferedReader = new BufferedReader
                            (new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                    //利用从HttpEntity中得到的String生成JsonObject
                    resultJsonObject = new JSONObject(entityStringBuilder.toString());
                } catch (Exception e) {
                    LOGGER.error("httpGet occur error : {}", e.getMessage());
                }
            }
            return resultJsonObject.toString();
        } catch (IOException e) {
            LOGGER.error("httpGet occur error : {}", e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error("httpGet occur error : {}", e.getMessage());
            }
        }
        return null;
    }

}