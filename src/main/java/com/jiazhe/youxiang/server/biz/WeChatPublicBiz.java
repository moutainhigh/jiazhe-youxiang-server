/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.alibaba.fastjson.JSON;
import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.server.common.enums.WeChatPublicCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPublicException;
import com.jiazhe.youxiang.server.dto.wechatpublic.WeChatAPIResultDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/8
 */
@Service("weChatPublicBiz")
public class WeChatPublicBiz {

    @Value("${wechat_public.appid}")
    private String WECHAT_PUBLIC_APPID;

    @Value("${wechat_public.secret}")
    private String WECHAT_PUBLIC_SECRET;


    private static String URL_API_GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static String URL_API_GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPublicBiz.class);

    /**
     * 获取签名
     *
     * @param timestamp
     * @param nonceStr
     * @param url
     * @return
     */
    public String getSignature(String timestamp, String nonceStr, String url) {
        //获得access_token
        String accessToken = getAccessToken();
        //获得jsapi_ticket
        if (Strings.isBlank(accessToken)) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.GET_ACCESS_TOKEN_ERROR);
        }
        String jsapiTicket = getJsapiTicket(accessToken);
        if (Strings.isBlank(jsapiTicket)) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.GET_JSAPI_TICKET_ERROR);
        }
        //执行签名算法并返回签名
        return createSignature(jsapiTicket, timestamp, nonceStr, url);
    }

    /**
     * 调用微信api获取公众号AccessToken
     *
     * @return
     */
    private String getAccessToken() {
        StringBuilder sbURL = new StringBuilder();
        sbURL.append(URL_API_GET_TOKEN);
        sbURL.append("&appid=").append(WECHAT_PUBLIC_APPID);
        sbURL.append("&secret=").append(WECHAT_PUBLIC_SECRET);
        String resultJson = HttpUtil.httpGet(sbURL.toString());
        if (Strings.isNotBlank(resultJson)) {
            WeChatAPIResultDTO weChatAPIResultDTO = JSON.parseObject(resultJson, WeChatAPIResultDTO.class);
            if (weChatAPIResultDTO != null && Strings.isNotBlank(weChatAPIResultDTO.getAccessToken())) {
                return weChatAPIResultDTO.getAccessToken();
            } else {
                LOGGER.error("getAccessToken发生错误，api请求返回值为:{}", resultJson);
                throw new WeChatPublicException(WeChatPublicCodeEnum.GET_ACCESS_TOKEN_ERROR);
            }
        }
        return null;
    }

    private String getJsapiTicket(String accessToken) {
        StringBuilder sbURL = new StringBuilder();
        sbURL.append(URL_API_GET_JSAPI_TICKET);
        sbURL.append("&access_token=").append(accessToken);
        String resultJson = HttpUtil.httpGet(sbURL.toString());
        if (Strings.isNotBlank(resultJson)) {
            WeChatAPIResultDTO weChatAPIResultDTO = JSON.parseObject(resultJson, WeChatAPIResultDTO.class);
            if (weChatAPIResultDTO != null && Strings.isNotBlank(weChatAPIResultDTO.getTicket())) {
                return weChatAPIResultDTO.getTicket();
            } else {
                LOGGER.error("getJsapiTicket发生错误，api请求返回值为:{}", resultJson);
                throw new WeChatPublicException(WeChatPublicCodeEnum.GET_JSAPI_TICKET_ERROR);
            }
        }
        return null;
    }

    /**
     * 生成签名
     * @param jsapiTicket
     * @param timestamp
     * @param nonceStr
     * @param url
     * @return
     */
    private String createSignature(String jsapiTicket, String timestamp, String nonceStr, String url) {
        /******
         签名生成规则如下：
         参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分） 。
         对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，使用URL键值对的格式 （即 key1=value1&key2=value2…）拼接成字符串string1。
         这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义。
         */
        StringBuilder sb = new StringBuilder();
        sb.append("jsapi_ticket=").append(jsapiTicket);
        sb.append("noncestr=").append(nonceStr);
        sb.append("timestamp=").append(timestamp);
        sb.append("url=").append(url);
        return DigestUtils.sha1Hex(sb.toString());
    }

}