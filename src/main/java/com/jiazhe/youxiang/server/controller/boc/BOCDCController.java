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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


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
    public Object queryStock(@RequestParam("xmlStr") String xmlStr, @RequestParam("sign") String sign) {
        LOGGER.info("HTTP调用[queryStock]方法，xmlStr:{},sign:{}", xmlStr, sign);
        BOCDCCommonReq req = getReq(xmlStr, sign);
        LOGGER.info("HTTP调用[queryStock]方法，参数:{}", JSONObject.toJSON(req));
        BOCDCUtils.adaptive(req);
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
            LOGGER.info("HTTP调用[queryStock]方法解密失败，message:{}", e.getMessage());
            return BOCDCUtils.genrateFailReturn();
        }
        BOCDCQueryStockResp resp = bocdcBiz.queryStock(queryStockReq);
        return BOCDCUtils.generateReturn(resp);
    }


    @AppApi
    @ApiOperation(value = "中行储蓄卡退货", httpMethod = "POST", response = BOCDCReverseValueResp.class, notes = "中行储蓄卡退货")
    @RequestMapping(value = "/reversevalue", method = RequestMethod.POST)
    public Object reverseValue(@ModelAttribute BOCDCCommonReq req) {
        LOGGER.info("HTTP调用[reverseValue]方法，参数:{}", JSONObject.toJSON(req));
        BOCDCUtils.adaptive(req);
        if (!BOCDCUtils.checkParam(req.getParam(), req.getSign())) {
            return BOCDCUtils.genrateFailReturn();
        }
        String paramStr = BOCDCUtils.trimBizData(req.getParam());
        paramStr = BOCDCUtils.xml2JsonStr(paramStr);
        BOCDCReverseValueReq reverseValueReq = JacksonUtil.readValue(paramStr, BOCDCReverseValueReq.class);
        try {
            reverseValueReq.setOrderNo(RSAUtil.bocdcPrivateDecrypt(reverseValueReq.getOrderNo()));
        } catch (Exception e) {
            LOGGER.info("HTTP调用[reverseValue]方法解密失败，message:{}", e.getMessage());
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

    private BOCDCCommonReq getReq(String xmlStr, String sign) {
        BOCDCCommonReq req = new BOCDCCommonReq();
        req.setXmlStr(xmlStr);
        req.setSign(sign);
        return req;
    }

    private BOCDCCommonReq getReq(HttpServletRequest request) {
        BOCDCCommonReq req = new BOCDCCommonReq();
        String reqStr = null;
        String param = null;
        String sign = null;
        try {
            reqStr = getRequestString(request);
            LOGGER.info("requestStr:{}", reqStr);
            reqStr = URLDecoder.decode(reqStr, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        if (!StringUtils.isEmpty(reqStr)) {
            String[] arr = reqStr.split("&");
            if (arr.length == 2) {
                param = arr[0];
                sign = arr[1];
                if (!StringUtils.isEmpty(param)) {
                    if ("param=".equals(param.substring(0, 6))) {
                        param = param.substring(6);
                    }
                }
                if (!StringUtils.isEmpty(sign)) {
                    if ("sign=".equals(sign.substring(0, 5))) {
                        sign = sign.substring(5);
                    }
                }
            }
        }
        LOGGER.info("## param:{},sign:{}", param, sign);
        req.setParam(param);
        req.setSign(sign);
        return req;
    }

    private String getRequestString(HttpServletRequest request) {
        String result = null;
        try {
            StringBuilder sb = new StringBuilder();
            InputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            LOGGER.error("读取请求内容失败", e);
        }
        LOGGER.error("getRequestString的结果：", result);
        return result;
    }
}
