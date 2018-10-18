/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.service.SysLogService;
import com.jiazhe.youxiang.server.vo.Paging;
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
     */
    public static void insert(String moduleName, String operate, int level, String ip, String detail) {
        executorService.execute(() -> {
            try {
                if (sysLogService != null) {
                    SysLogDTO sysLogDTO = new SysLogDTO();
                    sysLogDTO.setModuleName(moduleName);
                    sysLogDTO.setOperate(operate);
                    sysLogDTO.setLevel(level);
                    sysLogDTO.setOperatorId(0);
                    sysLogDTO.setOperatorName("test");
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

    public static List<SysLogDTO> getList(Integer type, Paging paging) {
        //这里面给paging的hasMore和total赋值
        return null;
    }
}