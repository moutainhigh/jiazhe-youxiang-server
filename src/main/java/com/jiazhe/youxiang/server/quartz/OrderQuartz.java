package com.jiazhe.youxiang.server.quartz;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author TU
 * @description
 * @date 2019/1/7.
 */
public class OrderQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderQuartz.class);
    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("测试定时任务");
    }
}
