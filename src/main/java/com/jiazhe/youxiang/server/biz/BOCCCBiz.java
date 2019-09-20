/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.HttpsClientRequestFactory;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.BOCCCBizCodeEnum;
import com.jiazhe.youxiang.server.common.enums.BOCCCCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.BOCCCException;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.vo.req.boc.BOCCCRefundReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCCCUsedReq;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCCCResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tu
 * @version 1.0
 * @description 中行信用卡业务层
 * @created 2019-09-16 19:57
 */
@Service("bocccBiz")
public class BOCCCBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(BOCCCBiz.class);
    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;

    /**
     * 中行信用卡实时接口：已使用请求
     */
    public static String REAL_TIME_USED_URL;

    /**
     * 中行信用卡实时接口：已退货请求
     */
    public static String REAL_TIME_REFUND_URL;

    @Value("${boccc.realtime.used_url}")
    public void setUsedUrl(String usedUrl) {
        REAL_TIME_USED_URL = usedUrl;
    }

    @Value("${boccc.realtime.refund_url}")
    public void setRefundUrl(String refundUrl) {
        REAL_TIME_REFUND_URL = refundUrl;
    }

    /**
     * 第一个实时接口 【退货信息接口】【中行请求第三方】
     *
     * @param data
     * @return
     */
    public BOCCCResp bocccRefundCheck(String data) {
        LOGGER.info("Biz调用[bocccRefundCheck]接口，data:{}", data);
        BOCCCResp resp = new BOCCCResp();
        try {
            String reqJson = RSAUtil.bocccPrivateDecrypt(data);
            if (null == reqJson) {
                resp.setStat(BOCCCBizCodeEnum.DECRYPT_EXCEPTIOM.getCode());
                resp.setResult(BOCCCBizCodeEnum.DECRYPT_EXCEPTIOM.getMessage());
            } else {
                BOCCCRefundReq req = null;
                try {
                    req = JacksonUtil.readValue(reqJson, BOCCCRefundReq.class);
                    PointExchangeCodeDTO dto = pointExchangeCodeBiz.findByKeyt(req.getwInfo());
                    if (null == dto) {
                        resp.setStat(BOCCCBizCodeEnum.CODE_NOT_EXIST.getCode());
                        resp.setResult(BOCCCBizCodeEnum.CODE_NOT_EXIST.getMessage());
                    } else {
                        if (dto.getUsed().equals(CommonConstant.CODE_NOT_USED)) {
                            pointExchangeCodeBiz.changeCodeUsedStatus(dto.getId(), CommonConstant.CODE_HAS_REFUND);
                            resp.setStat(BOCCCBizCodeEnum.SUCCESS.getCode());
                            resp.setResult(BOCCCBizCodeEnum.SUCCESS.getMessage());
                            resp.setDate(DateUtil.secondToStr(new Date()));
                        }
                        if (dto.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                            resp.setStat(BOCCCBizCodeEnum.CODE_HAS_USED.getCode());
                            resp.setResult(BOCCCBizCodeEnum.CODE_HAS_USED.getMessage());
                        }
                        if (dto.getUsed().equals(CommonConstant.CODE_HAS_REFUND)) {
                            resp.setStat(BOCCCBizCodeEnum.REFUND_AGAIN.getCode());
                            resp.setResult(BOCCCBizCodeEnum.REFUND_AGAIN.getMessage());
                        }
                    }
                } catch (Exception e) {
                    resp.setStat(BOCCCBizCodeEnum.DATA_FORMAT_EXCEPTION.getCode());
                    resp.setResult(BOCCCBizCodeEnum.DATA_FORMAT_EXCEPTION.getMessage());
                    LOGGER.error("请求参数异常，异常信息：" + e.getMessage());
                }
            }
        } catch (Exception e) {
            resp.setStat(BOCCCBizCodeEnum.ERROR.getCode());
            resp.setResult(BOCCCBizCodeEnum.ERROR.getMessage());
            LOGGER.error("中行请求第三方退货验证接口失败，异常信息：" + e.getMessage());
        }
        resp.setDate(DateUtil.secondToStr(new Date()));
        return resp;
    }

    /**
     * 第二个实时接口 【优惠券已使用更新接口】【三方请求中行】
     *
     * @param waresId
     * @param wEid
     * @param wInfo
     */
    @Async
    @Retryable(value = {BOCCCException.class},
            maxAttempts = 10, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public void bocccUsedUpdate(String waresId, String wEid, String wInfo) {
        LOGGER.info("Biz调用[bocccUsedUpdate]接口，waresId:{},wEid:{},wInfo:{}", waresId, wEid, wInfo);
        BOCCCUsedReq usedReq = new BOCCCUsedReq();
        usedReq.setWaresId(waresId);
        usedReq.setwEid(wEid);
        usedReq.setwInfo(wInfo);
        Map<String, String> req = new HashMap<>(2);
        String usedReqStr = JacksonUtil.toJSon(usedReq).replace("\"", "\\\"");
        try {
            String data = RSAUtil.bocccPublicEncrypt(usedReqStr);
            req.put("data", data);
        } catch (Exception e) {
            LOGGER.error("加密失败，usedReq:{}，message:{}", usedReqStr, e.getMessage());
            throw new BOCCCException(BOCCCCodeEnum.PARAM_ENCRYPT_ERROR.getCode(), BOCCCCodeEnum.PARAM_ENCRYPT_ERROR.getType(), e.getMessage());
        }
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        String result;
        try {
            LOGGER.info("HTTP调用中行信用卡使用状态更新实时接口，入参:{}", JSONObject.toJSON(req));
            ResponseEntity<String> response = restTemplate.getForEntity(REAL_TIME_USED_URL, String.class, req);
            result = new String(response.getBody().getBytes("ISO8859-1"), "utf-8");
            LOGGER.info("HTTP调用中行使用状态更新实时接口成功，入参:{}，返回值:{}", JSONObject.toJSON(req), JSONObject.toJSON(result));
        } catch (RestClientException | UnsupportedEncodingException e) {
            LOGGER.info("HTTP调用中行使用状态更新实时接口失败，RestClientException message:{}", e.getMessage());
            throw new BOCCCException(BOCCCCodeEnum.USED_UPDATE_ERROR.getCode(), BOCCCCodeEnum.USED_UPDATE_ERROR.getType(), e.getMessage());
        }
    }

    /**
     * 第三个实时接口 【优惠券退货更新接口】【三方请求中行】
     *
     * @param waresId
     * @param wEid
     * @param orderInfo
     * @param wInfo
     */
    @Async
    @Retryable(value = {BOCCCException.class},
            maxAttempts = 10, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public void bocccRefundUpdate(String waresId, String wEid, String orderInfo, String wInfo) {
        LOGGER.info("Biz调用[bocccRefundUpdate]接口，waresId:{},wEid:{},orderInfo:{},wInfo:{}", waresId, wEid, orderInfo, wInfo);
        BOCCCRefundReq refundReq = new BOCCCRefundReq();
        refundReq.setWaresId(waresId);
        refundReq.setwEid(wEid);
        refundReq.setOrderId(orderInfo);
        refundReq.setwInfo(wInfo);
        Map<String, String> req = new HashMap<>(2);
        String refundReqStr = JacksonUtil.toJSon(refundReq).replace("\"", "\\\"");
        try {
            String data = RSAUtil.bocccPublicEncrypt(refundReqStr);
            req.put("data", data);
        } catch (Exception e) {
            LOGGER.error("加密失败，refundReq：{}，message:{}", refundReqStr, e.getMessage());
            throw new BOCCCException(BOCCCCodeEnum.PARAM_ENCRYPT_ERROR.getCode(), BOCCCCodeEnum.PARAM_ENCRYPT_ERROR.getType(), e.getMessage());
        }
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        String result;
        try {
            LOGGER.info("HTTP调用中行信用卡退货更新实时接口，入参:{}", JSONObject.toJSON(req));
            ResponseEntity<String> response = restTemplate.postForEntity(REAL_TIME_REFUND_URL, req, String.class);
            result = new String(response.getBody().getBytes("ISO8859-1"), "utf-8");
            LOGGER.info("HTTP调用中行退货更新实时接口成功，入参:{}，返回值:{}", JSONObject.toJSON(req), JSONObject.toJSON(result));
        } catch (RestClientException | UnsupportedEncodingException e) {
            LOGGER.info("HTTP调用中行退货更新实时接口失败，RestClientException message:{}", e.getMessage());
            throw new BOCCCException(BOCCCCodeEnum.REFUND_UPDATE_ERROR.getCode(), BOCCCCodeEnum.REFUND_UPDATE_ERROR.getType(), e.getMessage());
        }
    }

}