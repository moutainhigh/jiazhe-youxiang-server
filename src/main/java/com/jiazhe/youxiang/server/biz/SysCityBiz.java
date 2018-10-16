/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.service.impl.SysCityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市管理Biz
 *
 * @author niexiao
 * @created 2018/10/16
 */
@Service("sysCityBiz")
public class SysCityBiz {

    @Autowired
    private SysCityServiceImpl sysCityService;

    public List<SysCityDTO> getList(String parentCode) {
        return sysCityService.getList(parentCode);
    }

    public void openCity(List<Integer> cityIds) {

    }
}