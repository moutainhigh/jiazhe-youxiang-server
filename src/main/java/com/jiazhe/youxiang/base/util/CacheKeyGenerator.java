/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存key生成器
 *
 * @author niexiao
 * @created 2018/12/7
 */
@Component("cacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("target", target.getClass().toGenericString());//放入target的名字
        map.put("method", method.getName());//放入method的名字
        if (params != null && params.length > 0) {//把所有参数放进去
            int i = 0;
            for (Object o : params) {
                map.put("params-" + i, o);
                i++;
            }
        }
        String str = JSONObject.toJSON(map).toString();
        byte[] hash = null;
        String s = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("generate error message:{}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("generate error message:{}", e.getMessage());
        }
        s = MD5Encoder.encode(hash);//使用MD5生成位移key
        return s;
    }

}
