/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.WeChatPayCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPayException;
import com.jiazhe.youxiang.server.controller.order.APIWeChatPayController;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author tu
 * @version 1.0
 * @description 微信支付业务逻辑
 * @created 2020-01-18 20:37
 */
@Service("weChatPayBiz")
public class WeChatPayBiz {

    private static final Logger logger = LoggerFactory.getLogger(WeChatPayBiz.class);

    //统一下单
    public UnifiedOrderResp unifiedOrder(WeChatUnifiedOrderReq req, HttpServletRequest request) {
        UnifiedOrderResp unifiedOrderResp = new UnifiedOrderResp();
        //需要保证参数的顺序
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("body", req.getBody());
        param.put("device_info", WeChatPayConstant.DEVICE_INFO);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("notify_url", WeChatPayConstant.DOMAIN + WeChatPayConstant.NOTIFY_URL);
        param.put("openid", req.getOpenId());
        param.put("out_trade_no", req.getOrderNo());
        param.put("spbill_create_ip", IpAdrressUtil.getIpAddress(request));
        param.put("total_fee", String.valueOf(req.getTotalFee()));
        param.put("trade_type", WeChatPayConstant.TRADE_TYPE);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.UNIFIEDORDER_URL, "POST", requestXml);
        System.out.println(result);
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals("SUCCESS")) {
                logger.info("发起预支付失败，原因：" + map.get("return_msg"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals("SUCCESS")) {
                logger.info("发起预支付失败，原因：" + map.get("err_code_des"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            //二次签名
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            SortedMap<String, String> finalPackage = new TreeMap<>();
            finalPackage.put("appId", WeChatPayConstant.APP_ID);
            finalPackage.put("timeStamp", timeStamp);
            finalPackage.put("nonceStr", nonceStr);
            finalPackage.put("package", "prepay_id=" + map.get("prepay_id"));
            finalPackage.put("signType", "MD5");
            String signAgain = WeChatPayUtils.createSign("UTF-8", finalPackage, WeChatPayConstant.API_KEY);
            unifiedOrderResp.setAppId(WeChatPayConstant.APP_ID);
            unifiedOrderResp.setTimeStamp(timeStamp);
            unifiedOrderResp.setNonceStr(nonceStr);
            unifiedOrderResp.setPrepayId(map.get("prepay_id"));
            unifiedOrderResp.setSignType("MD5");
            unifiedOrderResp.setPaySign(signAgain);
            return unifiedOrderResp;
        } catch (Exception e) {
            logger.info("发起预支付失败，异常信息：" + e.getMessage());
            throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
        }
    }

    //微信退款
    public void wechatRefund(String orderCode,Integer money) {
        //需要保证参数的顺序
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("notify_url", WeChatPayConstant.DOMAIN + WeChatPayConstant.NOTIFY_URL);
        param.put("out_refund_no", orderCode);
        param.put("out_trade_no", orderCode);
        param.put("refund_fee",  String.valueOf(money));
        param.put("total_fee", String.valueOf(money));
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.REFUND_URL, "POST", requestXml);
        System.out.println(result);
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals("SUCCESS")) {
                logger.info("发起退款失败，原因：" + map.get("return_msg"));
                throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals("SUCCESS")) {
                logger.info("发起退款失败，原因：" + map.get("err_code_des"));
                throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
            }
        } catch (Exception e) {
            logger.info("发起退款失败，异常信息：" + e.getMessage());
            throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
        }
    }
}
