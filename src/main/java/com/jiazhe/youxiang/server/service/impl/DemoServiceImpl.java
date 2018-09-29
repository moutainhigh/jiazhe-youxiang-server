/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dao.mapper.CouponAddressPOMapper;
import com.jiazhe.youxiang.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/8/13
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private CouponAddressPOMapper couponAddressPOMapper;

    @Override
    public String demo() {
        return couponAddressPOMapper.selectByPrimaryKey("2767f433-9496-49ef-af5f-2f820ea9966e").getAddress();
    }
}