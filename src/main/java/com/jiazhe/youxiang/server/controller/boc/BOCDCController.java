/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.boc;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.util.BOCDCUtils;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.server.biz.BOCDCBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCCommonReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCQueryStockReq;
import com.jiazhe.youxiang.server.vo.req.boc.BOCDCReverseValueReq;
import com.jiazhe.youxiang.server.vo.resp.DemoResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCQueryStockResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCReverseValueResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡（BOCDC）controller
 * @created 2019-09-08 11:11
 */
@RestController
@RequestMapping("externalapi/bocdc")
public class BOCDCController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BOCDCController.class);

    @Autowired
    private BOCDCBiz bocdcBiz;

    @AppApi
    @ApiOperation(value = "中行储蓄卡获取可用码", httpMethod = "POST", response = BOCDCQueryStockResp.class, notes = "中行储蓄卡获取可用码")
    @RequestMapping(value = "/querystock", method = RequestMethod.POST)
    public Object queryStock(@ModelAttribute BOCDCCommonReq req) {
        LOGGER.error("HTTP调用[queryStock]方法，参数:{}", JSONObject.toJSON(req));
        if (!BOCDCUtils.checkParam(req.getParam(), req.getSign())) {
            return BOCDCUtils.genrateFailReturn();
        }
        String paramStr = BOCDCUtils.trimBizData(req.getParam());
        paramStr = BOCDCUtils.xml2JsonStr(paramStr);
        BOCDCQueryStockReq queryStockReq = JacksonUtil.readValue(paramStr, BOCDCQueryStockReq.class);
        try {
            queryStockReq.setOrderNo(RSAUtil.bocdcPrivateDecrypt(queryStockReq.getOrderNo()));
            queryStockReq.setGiftNo(RSAUtil.bocdcPrivateDecrypt(queryStockReq.getGiftNo()));
        } catch (Exception e) {
            LOGGER.error("HTTP调用[queryStock]方法解密失败，message:{}", e.getMessage());
            return BOCDCUtils.genrateFailReturn();
        }
        BOCDCQueryStockResp resp = bocdcBiz.queryStock(queryStockReq);
        return BOCDCUtils.generateReturn(resp);
    }

    @AppApi
    @ApiOperation(value = "中行储蓄卡退货", httpMethod = "POST", response = BOCDCReverseValueResp.class, notes = "中行储蓄卡退货")
    @RequestMapping(value = "/reversevalue", method = RequestMethod.POST)
    public Object reverseValue(@ModelAttribute BOCDCCommonReq req) {
        LOGGER.error("HTTP调用[reverseValue]方法，参数:{}", JSONObject.toJSON(req));
        if (!BOCDCUtils.checkParam(req.getParam(), req.getSign())) {
            return BOCDCUtils.genrateFailReturn();
        }
        String paramStr = BOCDCUtils.trimBizData(req.getParam());
        paramStr = BOCDCUtils.xml2JsonStr(paramStr);
        BOCDCReverseValueReq reverseValueReq = JacksonUtil.readValue(paramStr, BOCDCReverseValueReq.class);
        try {
            reverseValueReq.setOrderNo(RSAUtil.bocdcPrivateDecrypt(reverseValueReq.getOrderNo()));
        } catch (Exception e) {
            LOGGER.error("HTTP调用[reverseValue]方法解密失败，message:{}", e.getMessage());
            return BOCDCUtils.genrateFailReturn();
        }
        BOCDCReverseValueResp resp = bocdcBiz.reverseValue(reverseValueReq);
        return BOCDCUtils.generateReturn(resp);
    }


    @AppApi
    @ApiOperation(value = "使用状态核对实时接口", httpMethod = "GET", response = DemoResp.class, notes = "使用状态核对实时接口")
    @RequestMapping(value = "/statusCheck", method = RequestMethod.GET)
    public Object statusCheck() throws Exception {
        long start = System.currentTimeMillis();

        bocdcBiz.statusCheck("2017042312", "1", "2018/02/20");
        //Future<String> task2 = bocdcBiz.statusCheck("abc", "435", "13242");
        //Future<String> task3 = bocdcBiz.statusCheck("213", "12673", "12342");

//        while (true) {
//            if (task1.isDone() ) {
//                // 三个任务都调用完成，退出循环等待
//                break;
//            }
//            Thread.sleep(1000);
//        }
        Thread.sleep(6000);
        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return "任务全部完成，总耗时：" + (end - start) + "毫秒";
    }
}
