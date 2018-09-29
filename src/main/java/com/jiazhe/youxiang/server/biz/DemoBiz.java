/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/8/13
 */
@Service("demoBiz")
public class DemoBiz {

    @Autowired
    private DemoService demoService;

    public String Demo() {
        return demoService.demo();
    }
}