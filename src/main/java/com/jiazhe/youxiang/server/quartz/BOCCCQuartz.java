package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.base.util.boccc.CouponUsedUtils;
import com.jiazhe.youxiang.base.util.boccc.SFTPUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author TU
 * @description  定时任务
 * @date 2019-09-03.
 */
public class BOCCCQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(BOCCCQuartz.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("中行定时任务执行开始执行");
        //定时下载文件（退货信息文件，每日优惠券剩余数量文件，每日商品购买清单文件）
        try {
            logger.info("定时任务下载文件执行中");
            SFTPUtils.download();
            logger.info("定时任务下载文件执行完成");
        } catch (Exception e) {
            logger.info("下载文件失败，异常信息：" + e.getMessage());
        }

        //定时分析退货信息，生成退货信息结果至上传文件夹
        try {

        } catch (Exception e) {

        }

        //定时生成前一日的使用情况
        try {
            logger.info("定时任务优惠券使用情况文件生成中");
            CouponUsedUtils.generateFile();
            logger.info("定时任务优惠券使用情况文件生成完成");
        } catch (Exception e) {

        }


        //定时上传指定文件夹的文件
        try {
            logger.info("定时任务上传文件执行中");
            SFTPUtils.upload();
            logger.info("定时任务上传文件执行完成");
        } catch (Exception e) {
            logger.info("定时任务上传文件执行失败，异常信息：" + e.getMessage());
        }

        //定时分析每日优惠券剩余数量
        try {

        } catch (Exception e) {

        }

        //定时分析每日商品购买清单
        try {

        } catch (Exception e) {

        }
        logger.info("中行定时任务执行完成");
    }
}
