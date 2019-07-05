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
import com.jiazhe.youxiang.server.dto.wechatpublic.SignatureDTO;
import com.jiazhe.youxiang.server.dto.wechatpublic.WeChatAPIResultDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

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

    @Value("${wechat_public.token}")
    private String WECHAT_PUBLIC_TOKEN;

    private static String URL_API_GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static String URL_API_GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";

    private static String CACHE_KEY_ACCESS_TOKEN = "access_token";
    private static String CACHE_KEY_JSAPI_TICKET = "jsapi_ticket";

    /**
     * 为减少对wechat api调用而加入的缓存，后续考虑用redis代替
     */
    public static ConcurrentHashMap<String, String> WECHAT_API_CACHE = new ConcurrentHashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPublicBiz.class);

    /**
     * 获取签名
     *
     * @param timestamp
     * @param nonceStr
     * @param url
     * @return
     */
    public SignatureDTO getSignature(String timestamp, String nonceStr, String url) {
        SignatureDTO signatureDTO = new SignatureDTO();
        String jsapiTicket = null;
        signatureDTO.setTimestamp(timestamp);
        signatureDTO.setNonceStr(nonceStr);
        signatureDTO.setUrl(url);
        if (Strings.isNotBlank(WECHAT_API_CACHE.get(CACHE_KEY_JSAPI_TICKET))) {
            jsapiTicket = WECHAT_API_CACHE.get(CACHE_KEY_JSAPI_TICKET);
            signatureDTO.setJsapiTicket(jsapiTicket);
        } else {
            //获得access_token
            String accessToken = getAccessToken();
            signatureDTO.setAccessToken(accessToken);
            //获得jsapi_ticket
            if (Strings.isBlank(accessToken)) {
                throw new WeChatPublicException(WeChatPublicCodeEnum.GET_ACCESS_TOKEN_ERROR);
            }
            jsapiTicket = getJsapiTicket(accessToken);
            signatureDTO.setJsapiTicket(jsapiTicket);
            if (Strings.isBlank(jsapiTicket)) {
                throw new WeChatPublicException(WeChatPublicCodeEnum.GET_JSAPI_TICKET_ERROR);
            }
        }
        //执行签名算法并返回签名
        String signature = createSignature(jsapiTicket, timestamp, nonceStr, url);
        signatureDTO.setSignature(signature);
        signatureDTO.setAppid(WECHAT_PUBLIC_APPID);
        return signatureDTO;
    }

    /**
     * 检验signature是否来自微信服务器
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        /******
         1）将token、timestamp、nonce三个参数进行字典序排序
         2）将三个参数字符串拼接成一个字符串进行sha1加密
         3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         */
        StringBuilder sb = new StringBuilder();
        sb.append(nonce).append(timestamp).append(WECHAT_PUBLIC_TOKEN);
        String checkSignature = DigestUtils.sha1Hex(sb.toString());
        return signature.equals(checkSignature);
    }

    /**
     * 调用微信api获取公众号AccessToken
     *
     * @return
     */
    private String getAccessToken() {
        if (Strings.isNotBlank(WECHAT_API_CACHE.get(CACHE_KEY_ACCESS_TOKEN))) {
            return WECHAT_API_CACHE.get(CACHE_KEY_ACCESS_TOKEN);
        }
        StringBuilder sbURL = new StringBuilder();
        sbURL.append(URL_API_GET_TOKEN);
        sbURL.append("&appid=").append(WECHAT_PUBLIC_APPID);
        sbURL.append("&secret=").append(WECHAT_PUBLIC_SECRET);
        String resultJson = HttpUtil.httpGet(sbURL.toString());
        if (Strings.isNotBlank(resultJson)) {
            WeChatAPIResultDTO weChatAPIResultDTO = JSON.parseObject(resultJson, WeChatAPIResultDTO.class);
            if (weChatAPIResultDTO != null && Strings.isNotBlank(weChatAPIResultDTO.getAccessToken())) {
                WECHAT_API_CACHE.put(CACHE_KEY_ACCESS_TOKEN, weChatAPIResultDTO.getAccessToken());
                return weChatAPIResultDTO.getAccessToken();
            } else {
                LOGGER.error("getAccessToken发生错误，api请求返回值为:{}", resultJson);
                throw new WeChatPublicException(WeChatPublicCodeEnum.GET_ACCESS_TOKEN_ERROR);
            }
        }
        return null;
    }

    /**
     * 调用微信api获取公众号JsapiTicket
     *
     * @param accessToken
     * @return
     */
    private String getJsapiTicket(String accessToken) {
        if (Strings.isNotBlank(WECHAT_API_CACHE.get(CACHE_KEY_JSAPI_TICKET))) {
            return WECHAT_API_CACHE.get(CACHE_KEY_JSAPI_TICKET);
        }
        StringBuilder sbURL = new StringBuilder();
        sbURL.append(URL_API_GET_JSAPI_TICKET);
        sbURL.append("&access_token=").append(accessToken);
        String resultJson = HttpUtil.httpGet(sbURL.toString());
        if (Strings.isNotBlank(resultJson)) {
            WeChatAPIResultDTO weChatAPIResultDTO = JSON.parseObject(resultJson, WeChatAPIResultDTO.class);
            if (weChatAPIResultDTO != null && Strings.isNotBlank(weChatAPIResultDTO.getTicket())) {
                WECHAT_API_CACHE.put(CACHE_KEY_JSAPI_TICKET, weChatAPIResultDTO.getTicket());
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
     *
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
        sb.append("&noncestr=").append(nonceStr);
        sb.append("&timestamp=").append(timestamp);
        sb.append("&url=").append(url);
        return DigestUtils.sha1Hex(sb.toString());
    }

}