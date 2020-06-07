/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.FileUtil;
import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.base.util.ShaUtils;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.base.util.boccc.PgpEncryUtil;
import com.jiazhe.youxiang.base.util.boccc.ZipUtil;
import com.jiazhe.youxiang.base.util.bocdc.BOCDCConstant;
import com.jiazhe.youxiang.base.util.bocdc.BOCDCUtils;
import com.jiazhe.youxiang.base.util.bocdc.SFTPUtils;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 中行储蓄卡Biz
 *
 * @author niexiao
 * @created 2019-09-09
 */
@Service("bocdcBiz")
public class BOCDCBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(BOCDCBiz.class);

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
        BOCDCQueryStockResp respUnencrypted = new BOCDCQueryStockResp();
        String orderNo;
        String giftNo;
        Integer validDate;
        try {
            orderNo = req.getOrderNo();
            giftNo = req.getGiftNo();
            validDate = Integer.valueOf(req.getValidDate());
            Date expiryDate = new Date(DateUtil.getLastSecond(System.currentTimeMillis() + CommonConstant.ONE_DAY * Integer.valueOf(validDate)));
            //先不判断有效期
            PointExchangeCodeDTO dto = null;
            synchronized (this) {
                //重试次数
                int retry = 0;
                while (dto == null && retry++ < 3) {
                    //100ms后重试
                    LOGGER.info("订单{}重试，当前是{}次，", req.getOrderNo(), retry);
                    TimeUnit.MILLISECONDS.sleep(100 * retry);
                    dto = pointExchangeCodeService.concurrencyQueryStock(orderNo, giftNo, null);
                }
            }
            if (dto == null) {
                //说明售卖不成功
                resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage());
                //TODO niexiao 删掉测试代码
                resp.setBizDesc(JSONObject.toJSONString(req));
            } else if (expiryDate.compareTo(dto.getExpiryTime()) == 1) {
                //说明查询到的积分码有效时间不足
                resp.setBizCode(BOCDCBizCodeEnum.MERCHANT_RETURNS_EXPIRY_DATE_ERROR.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.MERCHANT_RETURNS_EXPIRY_DATE_ERROR.getMessage());
            } else {
                resp.setBizCode(BOCDCBizCodeEnum.SUCCESS.getCode());
                resp.setBizDesc(BOCDCBizCodeEnum.SUCCESS.getMessage());
                //公钥加密
                resp.setGiftCardNo(RSAUtil.bocdcPublicEncrypt(dto.getCode()));
                resp.setGiftCardPwd(RSAUtil.bocdcPublicEncrypt(dto.getKeyt()));
                resp.setEbuyId(RSAUtil.bocdcPublicEncrypt(dto.getId().toString()));
                resp.setCardExpDate(DateUtil.yyyyMMDD(expiryDate));

                respUnencrypted.setGiftCardNo(dto.getCode());
                respUnencrypted.setGiftCardPwd(dto.getKeyt());
                respUnencrypted.setEbuyId(dto.getId().toString());
                respUnencrypted.setCardExpDate(DateUtil.yyyyMMDD(expiryDate));
            }
        } catch (Exception e) {
            resp.setBizCode(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getCode());
            resp.setBizDesc(BOCDCBizCodeEnum.MESSAGE_FORMAT_ERROR.getMessage() + " " + e.fillInStackTrace());
        }
        LOGGER.info("Biz调用[queryStock]方法成功，入参:{},出参:{},明文出参:{}", JacksonUtil.toJSon(req), JacksonUtil.toJSon(resp), JacksonUtil.toJSon(respUnencrypted));
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
            result = HttpUtil.sendPost(BOCDCConstant.STATUS_CHECK_HTTP_URL, postParam);
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
        } else if (!BOCDCConstant.CODE_SUCCESS.equals(commonResp.getBizCode())) {
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
     *
     * @param month 月份
     * @throws Exception
     */
    public void uploadReconciliationFile(int month) throws Exception {
        LOGGER.info("Biz执行[uploadReconciliationFile]方法");
        FileUtil.mkDirs(BOCDCConstant.reconciliationPath + "/" + getFristDateStringOfMonth(month));
        String sourceFileName = getFileName(BOCDCConstant.sourceFileName, month);
        String zipFileName = getFileName(BOCDCConstant.zipFileName, month);
        String pgpFileName = getFileName(BOCDCConstant.pgpFileName, month);
        //第1步，按照规则组成对账信息字符串
        String reconciliationInfoString = getReconciliationInfo(month);

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
        //第5步，上传加密文件
        LOGGER.info("对账信息加密压缩文件上传中...");
        uploadToSFTP(BOCDCConstant.outPath, new File(pgpFileName));
        LOGGER.info("对账信息加密压缩文件上传完成");
    }

    /**
     * 将从中行线上兑换且已过期的兑换码置为已使用
     */
    public void useExpiredCode() {
        LOGGER.info("Biz执行[useExpiredCode]方法");
        int result = pointExchangeCodeService.useExpiredCode();
        LOGGER.info("Biz执行[useExpiredCode]方法成功，共修改{}条记录", result);
    }


    /**
     * 生成对账信息字符串
     *
     * @return
     */
    private String getReconciliationInfo(int month) {
        StringBuilder sb = new StringBuilder();
        List<PointExchangeCodeDTO> dtoList = pointExchangeCodeService.getBOCDCReconciliationInfo(getFristDateOfMonth(month - 1), getLastDateOfMonth(month - 1));
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.stream().forEach(item -> {
                sb.append(item.getOutOrderCode());
                sb.append("|");
                sb.append(getUseStatus(item.getUsed().intValue()));
                sb.append("|");
                sb.append(BOCDCConstant.MER_ID);
                sb.append("\n");
            });
        }
        return sb.toString();
    }

    /**
     * 获得使用状态
     *
     * @param used
     * @return
     */
    private String getUseStatus(int used) {
        switch (used) {
            case 0:
            default:
                //未使用
                return "00";
            case 1:
                //已使用
                return "01";
            case 2:
                //已退货
                return "02";
        }
    }


    /**
     * 获得某月最后一天
     *
     * @param month
     * @return
     */
    private Date getLastDateOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获得某月第一天日期
     *
     * @param month
     * @return
     */
    private Date getFristDateOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        //一月是0
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获得某月第一天日期
     *
     * @param month
     * @return
     */
    private String getFristDateStringOfMonth(int month) {
        return DateUtil.yyyyMMDD(getFristDateOfMonth(month));
    }

    /**
     * 获得文件名
     *
     * @param fileName
     * @return
     */
    private String getFileName(String fileName, int month) {
        return BOCDCConstant.reconciliationPath + "/" + getFristDateStringOfMonth(month) + "/" + fileName.replace("#YYYYMMDD#", getFristDateStringOfMonth(month));
    }

    /**
     * 将文件上传到SFTP指定文件夹
     *
     * @param targetPath
     * @param file
     * @throws FileNotFoundException
     * @throws SftpException
     * @throws JSchException
     */
    private void uploadToSFTP(String targetPath, File file) throws FileNotFoundException, SftpException, JSchException {
        ChannelSftp channelSftp = null;
        String dstFilePath;
        try {
            // 一、 获取channelSftp对象
            channelSftp = SFTPUtils.getChannel(BOCDCConstant.username, null, BOCDCConstant.loginPrivateKeyPath, BOCDCConstant.host, BOCDCConstant.port);
            // 二、 判断远程路径dstDirPath是否存在(通道配置的路径)
            try {
                Vector dir = channelSftp.ls(targetPath);
                if (dir == null) { // 如果路径不存在，则创建
                    channelSftp.mkdir(targetPath);
                }
            } catch (SftpException e) { // 如果dstDirPath不存在，则会报错，此时捕获异常并创建dstDirPath路径
                channelSftp.mkdir(targetPath); // 此时创建路o如果再报错，即创建失败，则抛出异常
                e.printStackTrace();
            }
            // 三、 推送文件
            try {
                LOGGER.info("send the file : {}", file.getName());
                dstFilePath = targetPath + "/" + file.getName();
                LOGGER.info("the file all path is :{}", dstFilePath);
                // 推送: dstFilePath——传送过去的文件路径(全路径),采用默认的覆盖式推送
                channelSftp.put(new FileInputStream(file), dstFilePath); // jsch触发推送操作的方法
            } catch (SftpException e) {
                LOGGER.debug("An error occurred during sftp push, send data fail, the target path is :{}", targetPath);
                if (LOGGER.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        } finally {
            // 处理后事
            if (channelSftp != null) {
                channelSftp.quit();
            }
            try {
                SFTPUtils.closeChannel();
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        }
    }
}
