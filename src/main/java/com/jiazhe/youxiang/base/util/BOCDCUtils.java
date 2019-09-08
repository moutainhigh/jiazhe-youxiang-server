/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.logging.log4j.core.util.IOUtils;
import org.json.XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡工具类
 * @created 2019-09-08 9:30
 */
public class BOCDCUtils {

    private static String xml_prefix = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";


    /**
     * 拼接返回字符串
     *
     * @param jsonStr
     * @return
     */
    public static String generateReturn(Object jsonStr) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        StringBuilder sb = new StringBuilder(xml_prefix + generateLabel("data", false));
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsonObject.getString(key);
            sb.append(generateLabel(key, false)).append(value).append(generateLabel(key, true));
        }
        sb.append(generateLabel("data", true));
        return sb.toString();
    }

    /**
     * 检查入参报文是否正确
     *
     * @param param
     * @param sign
     * @return
     */
    public static void checkParam(String param, String sign) throws Exception{
        if(ShaUtils.getSha256(param).equals(sign)){
            throw new Exception("报文有误");
        }
    }

    public static String xml2JsonStr(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        String result = xmlSerializer.read(xml).toString();
        return result;
    }

    /**
     * 生成标签
     *
     * @param key
     * @param isEnd
     * @return
     */
    private static String generateLabel(String key, boolean isEnd) {
        return "<" + (isEnd ? "/" : "") + key + ">";
    }

}
