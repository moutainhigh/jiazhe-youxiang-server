package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.base.util.bocdc.BOCDCConstant;
import com.jiazhe.youxiang.server.biz.BOCDCBiz;
import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @author TU
 * @description 中行信用卡定时任务，根据不同环境判断是否执行
 * @date 2019-09-03.
 */
public class BOCDCQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(BOCDCQuartz.class);

    @Autowired
    private BOCDCBiz bocdcBiz;

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("BOCDC定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务开始执行");

        /**
         * 根据不同环境，判断此定时任务是否执行
         */
        if (!Arrays.asList(BOCDCConstant.BOCDC_ENVIRONMENT).contains(EnvironmentConstant.ENVIRONMENT)) {
            return;
        }
        //定时生成对账信息
        try {
            logger.info("将从中行线上兑换且已过期的兑换码置为已使用");
            bocdcBiz.useExpiredCode();
            logger.info("将从中行线上兑换且已过期的兑换码置为已使用完成");

            logger.info("生成生成对账信息文件");
            //获得当前月份，当前月份发送的是上个月的数据
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            bocdcBiz.uploadReconciliationFile(month);
            logger.info("生成生成对账信息文件完成");
        } catch (Exception e) {
            logger.error("生成生成对账信息文件失败，异常信息：" + e.getMessage());
        }

        logger.info("BOCDC定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务执行完成");
    }
}
