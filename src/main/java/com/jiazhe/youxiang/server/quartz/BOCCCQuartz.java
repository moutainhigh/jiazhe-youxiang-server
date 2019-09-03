package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.base.util.boccc.CouponUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author TU
 * @description
 * @date 2019-09-03.
 */
public class BOCCCQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(BOCCCQuartz.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("中行定时任务执行中");
        try {
            logger.info("生成优惠券文件");
            CouponUtils.generateFile();
        } catch (Exception e) {
            logger.info("生成优惠券文件失败，异常信息：" + e.getMessage());
        }
        logger.info("中行定时任务执行完成");
    }
}
