/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.server.biz.WeChatPublicBiz;
import org.apache.commons.collections.MapUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 微信api缓存任务
 *
 * @author niexiao
 * @created 2019/1/9
 */
public class WeChatAPICacheQuartz extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatAPICacheQuartz.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (MapUtils.isNotEmpty(WeChatPublicBiz.WECHAT_API_CACHE)) {
            LOGGER.info("清空微信api缓存");
            WeChatPublicBiz.WECHAT_API_CACHE.clear();
        }
    }
}