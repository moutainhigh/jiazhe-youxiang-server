/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.enums.DemoCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.DemoException;
import com.jiazhe.youxiang.server.vo.req.DemoReq;
import com.jiazhe.youxiang.server.vo.resp.DemoResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/8/13
 */
@RestController
@RequestMapping("api/demo")
public class DemoController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);


    @ApiOperation(value = "demo", httpMethod = "GET", response = DemoResp.class, notes = "接口的描述性文字")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object demo(@ModelAttribute DemoReq req) {

        DemoResp result = new DemoResp();
        result.setParam1("sfsdf");
        result.setParam2("resp" + req.getParam2());
        boolean error = false;
        if (error) {
            throw new DemoException(DemoCodeEnum.INSERT_GOODS_ACTIVITY_ERROR);
        }
        return result;
    }
}
