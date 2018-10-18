/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;

/**
 * 日志服务
 *
 * @author niexiao
 * @created 2018/10/18
 */
public interface SysLogService {

    int insert(SysLogDTO log);
}
