/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.SysLogService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
@Service("sysLogBiz")
public class SysLogBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogBiz.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static SysLogService sysLogService;


    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        SysLogBiz.sysLogService = sysLogService;
    }

    /**
     * 插入日志
     *
     * @param moduleName
     * @param operate
     * @param level
     * @param ip
     * @param detail
     */
    public static void insert(String moduleName, String operate, int level, String ip, String detail) {
        executorService.execute(() -> {
            try {
                if (sysLogService != null) {
                    SysLogDTO sysLogDTO = new SysLogDTO();
                    sysLogDTO.setModuleName(moduleName);
                    sysLogDTO.setOperate(operate);
                    sysLogDTO.setLevel(level);
                    SysUserDTO userDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
                    if (userDTO != null) {
                        sysLogDTO.setOperatorId(userDTO.getId());
                        sysLogDTO.setOperatorName(userDTO.getLoginName());
                    } else {
                        sysLogDTO.setOperatorId(0);
                        sysLogDTO.setOperatorName("未知");
                    }
                    sysLogDTO.setIp(ip);
                    sysLogDTO.setDetail(detail);
                    int success = sysLogService.insert(sysLogDTO);
                } else {
                    LOGGER.error("spring init bean sysLogService fail,please check configs");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public List<SysLogDTO> getList(String moduleName, String operate, Integer level, String operatorName, String ip, Paging paging) {
        //这里面给paging的hasMore和total赋值
        return sysLogService.getList(moduleName, operate, level, operatorName, ip, paging);
    }
}