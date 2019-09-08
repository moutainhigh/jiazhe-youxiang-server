/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.boc;

import com.jiazhe.youxiang.base.util.BOCDCUtils;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.server.biz.point.PointBiz;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.biz.point.PointExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCQueryStockReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCReverseValueReq;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCQueryStockResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCReverseValueResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡（BOCDC）controller
 * @created 2019-09-08 11:11
 */
@RestController
@RequestMapping("bocdc")
public class BOCDCController {

    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;
    @Autowired
    private PointExchangeRecordBiz pointExchangeRecordBiz;
    @Autowired
    private PointBiz pointBiz;

    private static String[] bizCode = {"0000", "2222", "9999"};
    private static String[] bizDesc = {"交易成功", "商户返回已使用", "报文格式错误"};

    @ApiOperation(value = "中行储蓄卡获取可用码", httpMethod = "POST", response = BOCDCQueryStockResp.class, notes = "中行储蓄卡获取可用码")
    @RequestMapping(value = "/queryStock", method = RequestMethod.POST)
    public Object queryStock(@RequestParam("param") String param, @RequestParam("sign") String sign) {
        BOCDCQueryStockResp resp = new BOCDCQueryStockResp();
        String orderNo;
        String giftNo;
        Integer validDate;
        try {
//            BOCDCUtils.checkParam(param, sign);
            String paramStr = BOCDCUtils.xml2JsonStr(param);
            BOCDCQueryStockReq req = JacksonUtil.readValue(paramStr, BOCDCQueryStockReq.class);
            orderNo = req.getOrderNo();
            giftNo = req.getGiftNo();
            validDate = Integer.valueOf(req.getValidDate());
//            orderNo = RSAUtil.bocdcPrivateDecrypt(req.getOrderNo());
//            giftNo = RSAUtil.bocdcPrivateDecrypt(req.getGiftNo());
            Date expiryDate = new Date(DateUtil.getLastSecond(System.currentTimeMillis() + CommonConstant.ONE_DAY * Integer.valueOf(validDate)));
            PointExchangeCodeDTO dto = pointExchangeCodeBiz.queryStock(orderNo, giftNo, expiryDate);
            if (null != dto) {
                resp.setBizCode(bizCode[0]);
                resp.setBizDesc(bizDesc[0]);
                resp.setGiftCardNo(dto.getCode());
                resp.setGiftCardPwd(dto.getKeyt());
//                resp.setGiftCardPwd(RSAUtil.bocdcPrivateEncrypt(dto.getKeyt()));
//                resp.setGiftCardNo(RSAUtil.bocdcPrivateEncrypt(dto.getCode()));
                resp.setCardExpDate(DateUtil.yyyyMMDD(dto.getExpiryTime()));
            }
        } catch (Exception e) {
            resp.setBizCode(bizCode[2]);
            resp.setBizDesc(bizDesc[2]);
        }
        return BOCDCUtils.generateReturn(resp);
    }

    @ApiOperation(value = "中行储蓄卡退货", httpMethod = "POST", response = BOCDCReverseValueResp.class, notes = "中行储蓄卡退货")
    @RequestMapping(value = "/reverseValue", method = RequestMethod.POST)
    public Object reverseValue(@RequestParam("param") String param, @RequestParam("sign") String sign) {
        BOCDCReverseValueResp resp = new BOCDCReverseValueResp();
        String orderNo;
        try {
//            BOCDCUtils.checkParam(param, sign);
            String paramStr = BOCDCUtils.xml2JsonStr(param);
            BOCDCReverseValueReq req = JacksonUtil.readValue(paramStr, BOCDCReverseValueReq.class);
//            orderNo = RSAUtil.bocdcPrivateDecrypt(req.getOrderNo());
            orderNo = req.getOrderNo();
            PointExchangeCodeDTO dto = pointExchangeCodeBiz.reverseValue(orderNo);
            if (null != dto) {
                if (dto.getUsed().equals(CommonConstant.CODE_NOT_USED)) {
                    resp.setBizCode(bizCode[0]);
                    resp.setBizDesc(bizDesc[0]);
                } else if(dto.getUsed().equals(CommonConstant.CODE_HAS_USED)){
                    PointExchangeRecordDTO pointExchangeRecordDto = pointExchangeRecordBiz.getByCodeId(dto.getId());
                    resp.setBizCode(bizCode[1]);
                    resp.setBizDesc(bizDesc[1]);
                    resp.setUseTime(DateUtil.yyyyMMDDhhmmss(pointExchangeRecordDto.getAddTime()));
                } else{
                    resp.setBizCode(bizCode[2]);
                    resp.setBizDesc(bizDesc[2]);
                }
            }
        } catch (Exception e) {
            resp.setBizCode(bizCode[2]);
            resp.setBizDesc(bizDesc[2]);
            return BOCDCUtils.generateReturn(resp);
        }
        return BOCDCUtils.generateReturn(resp);
    }
}
