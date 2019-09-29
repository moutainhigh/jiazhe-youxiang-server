/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.util.BOCDCUtils;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.base.util.ShaUtils;
import com.jiazhe.youxiang.server.biz.point.PointExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.BOCDCBizCodeEnum;
import com.jiazhe.youxiang.server.common.enums.BOCDCCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.BOCDCException;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCCommonReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCQueryStockReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCReverseValueReq;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCCommonResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCQueryStockResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCReverseValueResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 中行储蓄卡Biz
 *
 * @author niexiao
 * @created 2019-09-09
 */
@Service("bocdcBiz")
public class BOCDCBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(BOCDCBiz.class);

    private static final String CODE_SUCCESS = "0000";

    private static String MER_ID;

    private static String HTTP_URL;

    @Value("${bocdc.status_check.http_url}")
    public void setHttpUrl(String httpUrl) {
        HTTP_URL = httpUrl;
    }

    @Value("${bocdc.merid}")
    public void setMerId(String merId) {
        MER_ID = merId;
    }

    @Autowired
    private PointExchangeRecordBiz pointExchangeRecordBiz;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    /**
     * 查询库存订单下发实时接口
     */
    public BOCDCQueryStockResp queryStock(BOCDCQueryStockReq req) {
        BOCDCQueryStockResp resp = new BOCDCQueryStockResp();
        String orderNo;
        String giftNo;
        Integer validDate;
        try {
            orderNo = req.getOrderNo();
            giftNo = req.getGiftNo();
            validDate = Integer.valueOf(req.getValidDate());
            Date expiryDate = new Date(DateUtil.getLastSecond(System.currentTimeMillis() + CommonConstant.ONE_DAY * Integer.valueOf(validDate)));
            PointExchangeCodeDTO dto = pointExchangeCodeService.queryStock(orderNo, giftNo, expiryDate);
            if (dto == null) {
                //说明售卖不成功
                resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
                //TODO niexiao 删掉测试代码
                resp.setBizDesc(JSONObject.toJSONString(req));
            } else {
                resp.setBizCode(BOCDCBizCodeEnum.SUCCESS.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.SUCCESS.getMessage());
                //公钥加密
                resp.setGiftCardNo(RSAUtil.bocdcPublicEncrypt(dto.getCode()));
                resp.setGiftCardPwd(RSAUtil.bocdcPublicEncrypt(dto.getKeyt()));
                resp.setEbuyId(RSAUtil.bocdcPublicEncrypt(dto.getId().toString()));
                resp.setCardExpDate(DateUtil.yyyyMMDD(dto.getExpiryTime()));
            }
        } catch (Exception e) {
            resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
            resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
        }
        return resp;
    }

    /**
     * 积分冲正告知商户实时接口
     *
     * @param reverseValueReq
     * @return
     */
    public BOCDCReverseValueResp reverseValue(BOCDCReverseValueReq reverseValueReq) {
        BOCDCReverseValueResp resp = new BOCDCReverseValueResp();
        String orderNo;
        Integer pointExchangeCodeId;
        try {
            orderNo = reverseValueReq.getOrderNo();
            pointExchangeCodeId = Integer.valueOf(reverseValueReq.getEbuyId());
            PointExchangeCodeDTO dto = pointExchangeCodeService.queryByOrderNo(orderNo);
//            PointExchangeCodeDTO dto = pointExchangeCodeService.getById(pointExchangeCodeId);
            if (dto == null || !dto.getId().equals(pointExchangeCodeId)) {
                //说明没找到
                resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
                //TODO niexiao 删掉测试代码
                resp.setBizDesc(JSONObject.toJSONString(reverseValueReq));
            } else {
                if (dto.getUsed().equals(CommonConstant.CODE_NOT_USED)) {
                    resp.setBizCode(BOCDCBizCodeEnum.SUCCESS.getCode());
                    resp.setBizDesc(BOCDCBizCodeEnum.SUCCESS.getMessage());
                    //更新兑换码使用状态
                    pointExchangeCodeService.changeCodeUsedStatus(dto.getId(), CommonConstant.CODE_HAS_REFUND);
                } else if (dto.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                    PointExchangeRecordDTO pointExchangeRecordDto = pointExchangeRecordBiz.getByCodeId(dto.getId());
                    resp.setBizCode(BOCDCBizCodeEnum.MERCHANT_RETURNS_USED.getCode());
                    resp.setBizDesc(BOCDCBizCodeEnum.MERCHANT_RETURNS_USED.getMessage());
                    resp.setUseTime(DateUtil.yyyyMMDDhhmmss(pointExchangeRecordDto.getAddTime()));
                } else {
                    resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
                    resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
                }
            }
        } catch (Exception e) {
            resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
            resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
        }
        return resp;
    }

    /**
     * @param orderNo
     * @param useStatus
     * @param useTime
     */
    @Async
    @Retryable(value = {BOCDCException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 500L, multiplier = 2))
    public void statusCheck(String orderNo, String useStatus, String useTime) {
        LOGGER.info("Biz调用[statusCheck]接口，orderNo:{},useStatus:{},useTime:{}", orderNo, useStatus, useTime);
        BOCDCCommonReq req = new BOCDCCommonReq();
        try {
            //公钥加密
            orderNo = RSAUtil.bocdcPublicEncrypt(orderNo);
        } catch (Exception e) {
            LOGGER.error("加密失败，orderNo：{}，message:{}", orderNo, e.getMessage());
            throw new BOCDCException(BOCDCCodeEnum.STATUS_CHECK_ERROR.getCode(), BOCDCCodeEnum.STATUS_CHECK_ERROR.getType(), e.getMessage());
        }
        req.setParam(createParam(orderNo, useStatus, useTime));

        //使用SHA-256算法生成sign签名
        req.setSign(ShaUtils.getSha256(req.getParam()));
        RestTemplate restTemplate = new RestTemplate();
        String result;
        BOCDCCommonResp resp;
        try {
            LOGGER.info("HTTP调用中行使用状态核对实时接口，入参:{}", JSONObject.toJSON(req));
            ResponseEntity<String> response = restTemplate.postForEntity(HTTP_URL, req, String.class);
            result = new String(response.getBody().getBytes("ISO8859-1"), "utf-8");
            LOGGER.info("HTTP调用中行使用状态核对实时接口成功，入参:{}，返回值:{}", JSONObject.toJSON(req), JSONObject.toJSON(result));
        } catch (RestClientException | UnsupportedEncodingException e) {
            LOGGER.info("HTTP调用中行使用状态核对实时接口失败，RestClientException message:{}", e.getMessage());
            throw new BOCDCException(BOCDCCodeEnum.STATUS_CHECK_ERROR.getCode(), BOCDCCodeEnum.STATUS_CHECK_ERROR.getType(), e.getMessage());
        }
        result = BOCDCUtils.xml2JsonStr(result);
        BOCDCCommonResp commonResp = JacksonUtil.readValue(result, BOCDCCommonResp.class);
        if (commonResp == null) {
            String message = BOCDCCodeEnum.STATUS_CHECK_ERROR.getMessage() + "orderNo:" + orderNo + ",useStatus:" + useStatus + ",useTime:" + useTime;
            LOGGER.info("Biz调用[statusCheck]接口失败,message:{}", message);
            throw new BOCDCException(BOCDCCodeEnum.STATUS_CHECK_ERROR.getCode(), BOCDCCodeEnum.STATUS_CHECK_ERROR.getType(), message);
        } else if (commonResp.getBizCode() != CODE_SUCCESS) {
            String message = BOCDCCodeEnum.STATUS_CHECK_ERROR.getMessage() + "orderNo:" + orderNo + ",useStatus:" + useStatus + ",useTime:" + useTime;
            LOGGER.info("Biz调用[statusCheck]接口成功，但返回值不符合预期，返回值:{} message:{}", JSONObject.toJSON(commonResp), message);
        } else {
            LOGGER.info("Biz调用[statusCheck]接口成功，orderNo:{},useStatus:{},useTime:{}", orderNo, useStatus, useTime);
        }
    }


    private String createParam(String orderNo, String useStatus, String useTime) {
        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"  standalone=\"yes\"?>" +
                "<bizdata><data>" +
                "<orderNo>%s</orderNo>" +
                "<useStatus>%s</useStatus>" +
                "<useTime>%s</useTime>" +
                "<merId>%s</merId>" +
                "</data></bizdata>";
        String result = String.format(template, orderNo, useStatus, useTime, MER_ID);
        return result.trim().replaceAll("\r|\n", "");
    }


    @Recover
    public void recover(BOCDCException e) {
        //TODO niexiao 多次重试失败，后期可以加上短信通知
        LOGGER.info("多次调用使用状态核对实时接口失败，message:{}", e.getMessage());
    }


}

