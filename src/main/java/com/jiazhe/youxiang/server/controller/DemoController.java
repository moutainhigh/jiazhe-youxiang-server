/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.server.biz.DemoBiz;
import com.jiazhe.youxiang.server.vo.req.DemoReq;
import com.jiazhe.youxiang.server.vo.resp.DemoResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("server/demo")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private DemoBiz demoBiz;

    @Value("${environment}")
    private String ENVIRONMENT;


    @RequestMapping(value = "/environment", method = RequestMethod.GET)
    public String environment() {
        LOGGER.info("environment");
        return demoBiz.Demo();
    }

    @ApiOperation(value = "demo", httpMethod = "GET", response = DemoResp.class, notes = "接口的描述性文字")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object demo(@ModelAttribute DemoReq req) {

        DemoResp result = new DemoResp();
        result.setParam1("sfsdf");
        result.setParam2("resp" + req.getParam2());
        return result;
    }
}
