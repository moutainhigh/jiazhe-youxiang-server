/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.WeChatPayCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPayException;
import com.jiazhe.youxiang.server.controller.order.APIWeChatPayController;
import com.jiazhe.youxiang.server.dto.order.orderinfo.TenpayQureyDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.security.KeyStore;
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

    private static String SUCCESS = "SUCCESS";

    private static final Logger logger = LoggerFactory.getLogger(WeChatPayBiz.class);

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    //统一下单，三方请求微信
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

    //微信退款，三方请求微信
    public void wechatRefund(String orderCode, Integer money) {
        //需要保证参数的顺序
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("notify_url", WeChatPayConstant.DOMAIN + WeChatPayConstant.REFUND_NOTIFY_URL);
        param.put("out_refund_no", orderCode);
        param.put("out_trade_no", orderCode);
        param.put("refund_fee", String.valueOf(money));
        param.put("total_fee", String.valueOf(money));
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream("/opt/jiazhe/webserver/files/tencentRefundCert/apiclient_cert.p12");
            try {
                // 指定PKCS12的密码(商户ID)
                clientStore.load(instream, WeChatPayConstant.MCH_ID.toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(clientStore, WeChatPayConstant.MCH_ID.toCharArray()).build();
            // 指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            // 设置httpclient的SSLSocketFactory
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            try {
                HttpPost httpost = new HttpPost(WeChatPayConstant.REFUND_URL);
                httpost.setEntity(new StringEntity(requestXml, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();
                    String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(entity);
                    Map<String, String> map = WeChatPayUtils.doXMLParse(result);
                    String returnCode = map.get("return_code");
                    if (!returnCode.equalsIgnoreCase("SUCCESS")) {
                        logger.info("发起退款失败，原因：" + map.get("return_msg"));
                        throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
                    }
                    String resultCode = map.get("result_code");
                    if (!resultCode.equalsIgnoreCase("SUCCESS")) {
                        logger.info("发起退款失败，原因：" + map.get("err_code_des"));
                        throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查是否已经微信支付
     *
     * @param orderCode
     * @return
     */
    public TenpayQureyDTO checkTenPay(String orderCode) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("out_trade_no", orderCode);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.ORDER_QUERY_URL, "POST", requestXml);
        System.out.println(result);
        TenpayQureyDTO dto = new TenpayQureyDTO();
        dto.setTradeState("FAIL");
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals(SUCCESS)) {
                dto.setReason(map.get("return_msg"));
                logger.info("微信支付订单查询失败，原因：" + map.get("return_msg"));
                return dto;
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals(SUCCESS)) {
                logger.info("微信支付订单查询失败，原因：" + map.get("err_code_des"));
                dto.setReason(map.get("err_code_des"));
                return dto;
            }
            String tradeState = map.get("trade_state");
            if (tradeState.equals(SUCCESS)) {
                dto.setTradeState("SUCCESS");
                dto.setTotalFee(new Integer(map.get("total_fee")));
                dto.setTransactionId(map.get("transaction_id"));
                orderInfoBiz.wxNotify(dto.getTransactionId(), orderCode, dto.getTotalFee());
                return dto;
            } else {
                logger.info(map.get("trade_state_desc"));
                dto.setReason(map.get("trade_state_desc"));
                return dto;
            }
        } catch (Exception e) {
            logger.info("微信支付订单查询失败，异常信息：" + e.getMessage());
            dto.setReason("查询失败，未知异常");
        }
        return dto;
    }

    /**
     * 检查是否已经微信退款
     *
     * @param orderCode
     * @return
     */
    public TenpayQureyDTO checkTenPayRefund(String orderCode) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("out_trade_no", orderCode);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.ORDER_REFUND_QUERY_URL, "POST", requestXml);
        System.out.println(result);
        TenpayQureyDTO dto = new TenpayQureyDTO();
        dto.setTradeState("FAIL");
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals(SUCCESS)) {
                dto.setReason(map.get("return_msg"));
                logger.info("微信退款查询失败，原因：" + map.get("return_msg"));
                return dto;
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals(SUCCESS)) {
                logger.info("微信退款查询失败，原因：" + map.get("err_code_des"));
                dto.setReason(map.get("err_code_des"));
                return dto;
            }
            String refundState = map.get("refund_status_0");
            if (SUCCESS.equals(refundState)) {
                dto.setTradeState("SUCCESS");
                dto.setTotalFee(new Integer(map.get("refund_fee_0")));
                dto.setTransactionId(map.get("refund_id_0"));
                orderInfoBiz.wxRefundNotify(dto.getTransactionId(), orderCode, dto.getTotalFee());
            } else if ("PROCESSING".equals(refundState)) {
                dto.setReason("退款处理中");
            } else if ("REFUNDCLOSE".equals(refundState)) {
                dto.setReason("退款已关闭");
            } else {
                dto.setReason("退款异常，请联系商户");
            }
            return dto;
        } catch (Exception e) {
            logger.info("微信支付订单查询失败，异常信息：" + e.getMessage());
            dto.setReason("查询失败，未知异常");
        }
        return dto;
    }

    //测试退款
    public static void main(String[] args) throws Exception {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", "wx02f6f30dc9c7123e");
        param.put("mch_id", "1541609211");
        String nonceStr = RandomUtil.generateCode(32);
        param.put("nonce_str", nonceStr);
        //param.put("notify_url", WeChatPayConstant.DOMAIN + WeChatPayConstant.REFUND_NOTIFY_URL);
        param.put("out_refund_no", "2020011700001");
        param.put("out_trade_no", "2020011700001");
        param.put("refund_fee", "1");
        param.put("total_fee", "1");
        String sign = WeChatPayUtils.createSign("UTF-8", param, "beijingchengyi20190625chengyi625");
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream("A:/opt/cert/apiclient_cert.p12");
            try {
                // 指定PKCS12的密码(商户ID)
                clientStore.load(instream, "1541609211".toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(clientStore, "1541609211".toCharArray()).build();
            // 指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            // 设置httpclient的SSLSocketFactory
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            try {
                HttpPost httpost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
                httpost.setEntity(new StringEntity(requestXml, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();
                    String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(result);
                    EntityUtils.consume(entity);
                    Map<String, String> map = WeChatPayUtils.doXMLParse(result);
                    String returnCode = map.get("return_code");
                    if (!returnCode.equalsIgnoreCase("SUCCESS")) {
                        System.out.println("发起退款失败，原因：" + map.get("return_msg"));
                        throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
                    }
                    String resultCode = map.get("result_code");
                    if (!resultCode.equalsIgnoreCase("SUCCESS")) {
                        System.out.println("发起退款失败，原因：" + map.get("err_code_des"));
                        throw new WeChatPayException(WeChatPayCodeEnum.WECHAT_REFUND_ERROR);
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
