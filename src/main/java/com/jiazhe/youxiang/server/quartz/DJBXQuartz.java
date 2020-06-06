package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.server.biz.djbx.DJBXBiz;
import com.jiazhe.youxiang.server.common.constant.DJBXConstant;
import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Arrays;

/**
 * @author TU
 * @description  大家保险定时任务
 * @date 2020-05-22.
 */
public class DJBXQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(DJBXQuartz.class);

    @Autowired
    private DJBXBiz djbxBiz;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("DJBX定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务开始执行");

        /**
         * 根据不同环境，判断此定时任务是否执行
         */
        if (!Arrays.asList(DJBXConstant.DJBX_ENVIRONMENT).contains(EnvironmentConstant.ENVIRONMENT)) {
            return;
        }

//        djbxBiz.getPointsToken();

        logger.info("DJBX定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务执行完成");

    }
}
