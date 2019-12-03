/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.FileUtil;
import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.base.util.ShaUtils;
import com.jiazhe.youxiang.base.util.boccc.AutoSFTPUtils;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.base.util.boccc.PgpEncryUtil;
import com.jiazhe.youxiang.base.util.boccc.ZipUtil;
import com.jiazhe.youxiang.base.util.bocdc.BOCDCConstant;
import com.jiazhe.youxiang.base.util.bocdc.BOCDCUtils;
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
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private static final String MERCHANTSOURCE = "0000";


    @Autowired
    private PointExchangeRecordBiz pointExchangeRecordBiz;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    /**
     * 查询库存订单下发实时接口
     */
    public BOCDCQueryStockResp queryStock(BOCDCQueryStockReq req) {
        LOGGER.info("Biz调用[queryStock]方法，入参:{}", JacksonUtil.toJSon(req));
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
        LOGGER.info("Biz调用[queryStock]方法成功，入参:{},出参:{}", JacksonUtil.toJSon(req), JacksonUtil.toJSon(resp));
        return resp;
    }

    /**
     * 积分冲正告知商户实时接口
     *
     * @param reverseValueReq
     * @return
     */
    public BOCDCReverseValueResp reverseValue(BOCDCReverseValueReq reverseValueReq) {
        LOGGER.info("Biz调用[reverseValue]接口，入参:{}", JacksonUtil.toJSon(reverseValueReq));
        BOCDCReverseValueResp resp = new BOCDCReverseValueResp();
        String orderNo;
        Integer pointExchangeCodeId;
        try {
            orderNo = reverseValueReq.getOrderNo();
            pointExchangeCodeId = Integer.valueOf(reverseValueReq.getEbuyId());
            PointExchangeCodeDTO dto = pointExchangeCodeService.queryByOrderNo(orderNo);
//            PointExchangeCodeDTO dto = pointExchangeCodeService.getById(pointExchangeCodeId);
            LOGGER.info("尝试找到兑换码记录，orderNo:{},pointExchangeCodeId:{},PointExchangeCodeDTO:{},", orderNo, pointExchangeCodeId, JacksonUtil.toJSon(dto));
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
            maxAttempts = 10,
            backoff = @Backoff(delay = 5000L, multiplier = 2))
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
        String result;
        try {
            String postParam = getParamString(req);
            LOGGER.info("HTTP调用中行使用状态核对实时接口，入参:{}", postParam);
            result = HttpUtil.sendPost(BOCDCConstant.HTTP_URL, postParam);
            LOGGER.info("HTTP调用中行使用状态核对实时接口成功，入参:{}，返回值:{}", postParam, JSONObject.toJSON(result));
        } catch (RestClientException e) {
            LOGGER.info("HTTP调用中行使用状态核对实时接口失败，RestClientException message:{}", e.getMessage());
            throw new BOCDCException(BOCDCCodeEnum.STATUS_CHECK_ERROR.getCode(), BOCDCCodeEnum.STATUS_CHECK_ERROR.getType(), e.getMessage());
        }
        result = BOCDCUtils.xml2JsonStr(result.trim());
        BOCDCCommonResp commonResp = JacksonUtil.readValue(result, BOCDCCommonResp.class);
        if (commonResp == null) {
            String message = BOCDCCodeEnum.STATUS_CHECK_ERROR.getMessage() + "orderNo:" + orderNo + ",useStatus:" + useStatus + ",useTime:" + useTime;
            LOGGER.info("Biz调用[statusCheck]接口失败,message:{}", message);
            throw new BOCDCException(BOCDCCodeEnum.STATUS_CHECK_ERROR.getCode(), BOCDCCodeEnum.STATUS_CHECK_ERROR.getType(), message);
        } else if (!CODE_SUCCESS.equals(commonResp.getBizCode())) {
            String message = BOCDCCodeEnum.STATUS_CHECK_ERROR.getMessage() + "orderNo:" + orderNo + ",useStatus:" + useStatus + ",useTime:" + useTime;
            LOGGER.info("Biz调用[statusCheck]接口成功，但返回值不符合预期，返回值:{} message:{}", JSONObject.toJSON(commonResp), message);
        } else {
            LOGGER.info("Biz调用[statusCheck]接口成功，orderNo:{},useStatus:{},useTime:{}", orderNo, useStatus, useTime);
        }
    }

    private String getParamString(BOCDCCommonReq req) {
        StringBuilder sb = new StringBuilder();
        String param = "";
        try {
            param = URLEncoder.encode(req.getParam(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("对参数encode发生错误，message:{}", e.getMessage());
        }
        sb.append("param=").append(param).append("&sign=").append(req.getSign());
        return sb.toString();
    }


    private String createParam(String orderNo, String useStatus, String useTime) {
        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"  standalone=\"yes\"?>" +
                "<bizdata><data>" +
                "<orderNo>%s</orderNo>" +
                "<useStatus>%s</useStatus>" +
                "<useTime>%s</useTime>" +
                "<merId>%s</merId>" +
                "</data></bizdata>";
        String result = String.format(template, orderNo, useStatus, useTime, BOCDCConstant.MER_ID);
        return result.trim().replaceAll("\r|\n", "");
    }

    @Recover
    public void recover(BOCDCException e) {
        //TODO niexiao 多次重试失败，后期可以加上短信通知
        LOGGER.info("多次调用使用状态核对实时接口失败，message:{}", e.getMessage());
    }


    /**
     * 生成并上传对账文件
     */
    public void uploadReconciliationFile() throws Exception {
        LOGGER.info("Biz执行[uploadReconciliationFile]方法");
        FileUtil.mkDirs(BOCDCConstant.reconciliationPath + "/" + getFristDayOfThisMonth());
        String sourceFileName = getFileName(BOCDCConstant.sourceFileName);
        String zipFileName = getFileName(BOCDCConstant.zipFileName);
        String pgpFileName = getFileName(BOCDCConstant.pgpFileName);
        String uploadPath = BOCDCConstant.uploadPath + "/" + getFristDayOfThisMonth();
        //第1步，按照规则组成对账信息字符串
        String reconciliationInfoString = getReconciliationInfo();

        //TODO niexiao
        //第2步，写入文件中
        LOGGER.info("对账信息源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, reconciliationInfoString);
        LOGGER.info("对账信息源文件生成完成，路径为：" + sourceFileName);

        //第3步，源文件压缩中
        LOGGER.info("对账信息源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        LOGGER.info("对账信息源文件压缩完成，路径为：" + zipFileName);

        //第4步，压缩文件加密中
        LOGGER.info("对账信息压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCDCConstant.publicKeyPath, pgpFileName);
        LOGGER.info("对账信息压缩文件加密完成，路径为：" + pgpFileName);

        //第5步，复制加密文件至upload里
        FileUtil.copyToPath(pgpFileName, uploadPath);

        //第6步，上传加密文件
        AutoSFTPUtils.upload(BOCDCConstant.username, BOCDCConstant.host, BOCDCConstant.port, BOCDCConstant.loginPrivateKeyPath, uploadPath, BOCDCConstant.outPath);
    }


    /**
     * 生成对账信息字符串
     *
     * @return
     */
    private String getReconciliationInfo() {
        StringBuilder sb = new StringBuilder();
        List<String> reconciliationInfoList = Lists.newArrayList();
        List<PointExchangeCodeDTO> dtoList = pointExchangeCodeService.getBOCDCReconciliationInfo(getBeginDate(), getEndDate());
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.stream().forEach(item -> {
                sb.append(item.getOutOrderCode());
                sb.append("|");
                sb.append(getUseStatus(item.getUsed().intValue()));
                sb.append("|");
                sb.append(MERCHANTSOURCE);
                reconciliationInfoList.add(sb.toString());
                sb.delete(0, sb.length());
            });
        }
        return String.join("\r\n", reconciliationInfoList);
    }

    private String getUseStatus(int used) {
        //TODO niexiao 整理代码
        switch (used) {
            case 0:
            default:
                return "00";
            case 1:
                return "01";
            case 2:
                return "02";
        }
    }

    /**
     * 获得上月1日
     */
    private Date getBeginDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    private Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获得本月第一天日期
     *
     * @return
     */
    private String getFristDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return DateUtil.yyyyMMDD(calendar.getTime());
    }

    private String getFileName(String fileName) {
        return BOCDCConstant.reconciliationPath + "/" + getFristDayOfThisMonth() + "/" + fileName.replace("#YYYYMMDD#", getFristDayOfThisMonth());
    }
}
