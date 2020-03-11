/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.boc;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.base.util.bocdc.BOCDCUtils;
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
    public Object queryStock(HttpServletRequest request) {
//        LOGGER.error("HTTP调用[queryStock]方法，request:{}", JSONObject.toJSON(request));
        BOCDCCommonReq req = getReq(request);
        LOGGER.info("HTTP调用[queryStock]方法，参数:{}", JSONObject.toJSON(req));
        if (!BOCDCUtils.checkParam(req.getParam(), req.getSign())) {
            LOGGER.info("HTTP调用[queryStock]方法,入参没有通过检查，参数:{}", JSONObject.toJSON(req));
            return BOCDCUtils.genrateFailReturn();
        }
        String paramStr = BOCDCUtils.trimBizData(req.getParam());
        LOGGER.info("BOCDCUtils.trimBizData paramStr:{}", paramStr);
        paramStr = BOCDCUtils.xml2JsonStr(paramStr);
        LOGGER.info("BOCDCUtils.xml2JsonStr paramStr:{}", paramStr);
        BOCDCQueryStockReq queryStockReq = JacksonUtil.readValue(paramStr, BOCDCQueryStockReq.class);
        LOGGER.info("JacksonUtil.readValue queryStockReq:{}", JSONObject.toJSON(queryStockReq));
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
    public Object reverseValue(HttpServletRequest request) {
        BOCDCCommonReq req = getReq(request);
        LOGGER.info("HTTP调用[reverseValue]方法，参数:{}", JSONObject.toJSON(req));
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

        bocdcBiz.statusCheck("1000028593", "1", "20191014160353");
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

    @AppApi
    @ApiOperation(value = "上传对账信息", httpMethod = "GET", notes = "上传对账信息")
    @RequestMapping(value = "/uploadreconciliationfile", method = RequestMethod.GET)
    public Object uploadReconciliationFile(@RequestParam("monthOffset") int monthOffset) throws Exception {
        bocdcBiz.uploadReconciliationFile(monthOffset);
        return "任务全部完成";
    }

    @AppApi
    @ApiOperation(value = "将从中行线上兑换且已过期的兑换码置为已使用", httpMethod = "GET", notes = "将从中行线上兑换且已过期的兑换码置为已使用")
    @RequestMapping(value = "/useexpiredcode", method = RequestMethod.GET)
    public Object useExpiredCode() {
        bocdcBiz.useExpiredCode();
        return "任务全部完成";
    }


    /**
     * 通过请求报文获取请求参数
     * 测试报文
     * http://152.136.33.239:8081/externalapi/bocdc/querystock&xmlStr=param=%3C%3Fxml+version%3D%271.0%27+encoding%3D%27UTF-8%27+standalone%3D%27yes%27%3F%3E%3Cdata%3E%3CorderNo%3EUuIAKtFezIjzywD5Dh7BpvmpEBJ3uFvW%2BcPUfZ7DkWgtmQESVBduER4M0XRay9qdSw5L7CGl5N2sG3yqD0h7l8Qx1cgMO5LQwy04pS7TUNn7NwUeFIJFLmuNpNbHoOQkhBo7QjXIcmz9v%2BFxPpqvGbqm5k53E6NwmndNx02nmJo%3D%3C%2ForderNo%3E%3CtranDate%3E20190924113701%3C%2FtranDate%3E%3CorderStatus%3E04%3C%2ForderStatus%3E%3CvalidDate%3E15%3C%2FvalidDate%3E%3CgiftNo%3EIlcNPksPwvfcsCkWe4NnJzbtVz%2FvLLu3TQj3FxRSj8RZ5IDhyYbdJHhHX7trSHHC82erlf0d1zgavZ4r7lj8f51KTojdRCOMeH1JGBGZgoZCpeEAKs71dcnjIv%2FLYaYPLx4zQpEbKAPeIsffHNkLmgFjLgwh0F%2Bt6bX%2B%2FSq9PM4%3D%3C%2FgiftNo%3E%3C%2Fdata%3E&sign=ca4f23b1ea1aed296c4452957ee0a4b90e6cee5a1d117b9f2624d9abfc533bc2
     *
     * @param request
     * @return
     */
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
            LOGGER.error("解析报文失败，message:{}", e);
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
        LOGGER.info("解析报文成功，param:{},sign:{}", param, sign);
        req.setParam(param);
        req.setSign(sign);
        BOCDCUtils.adaptive(req);
        return req;
    }

    /**
     * 获取请求内容
     *
     * @param request
     * @return
     */
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
        LOGGER.info("getRequestString的结果：{}", result);
        return result;
    }
}
