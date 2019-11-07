/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.BOCDCBizCodeEnum;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCCommonReq;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCCommonResp;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang.StringUtils;

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
        StringBuilder sb = new StringBuilder(xml_prefix);
        sb.append(generateLabel("bizdata", false));
        sb.append(generateLabel("data", false));
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsonObject.getString(key);
            sb.append(generateLabel(key, false)).append(value).append(generateLabel(key, true));
        }
        sb.append(generateLabel("data", true));
        sb.append(generateLabel("bizdata", true));
        return sb.toString();
    }

    /**
     * 检查入参报文是否正确
     *
     * @param param
     * @param sign
     * @return
     */
    public static boolean checkParam(String param, String sign) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        if (!ShaUtils.getSha256(param).equals(sign)) {
            return false;
        }
        return true;
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

    /**
     * 生成失败返回值
     *
     * @return
     */
    public static Object genrateFailReturn() {
        BOCDCCommonResp resp = new BOCDCCommonResp();
        resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
        resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
        return generateReturn(resp);
    }

    public static String trimBizData(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.replaceAll("<bizdata>", "").replaceAll("</bizdata>", "");
        }
        return str;

    }

    public static void adaptive(BOCDCCommonReq req) {
        if (req == null || StringUtils.isEmpty(req.getXmlStr()) || StringUtils.isNotEmpty(req.getParam())) {
            return;
        }
        req.setParam(req.getXmlStr().replace("param=", ""));
    }
}
