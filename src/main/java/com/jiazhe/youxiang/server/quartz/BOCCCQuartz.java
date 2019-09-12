package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.base.util.boccc.AutoCouponUsedUtils;
import com.jiazhe.youxiang.base.util.boccc.AutoSFTPUtils;
import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Arrays;

/**
 * @author TU
 * @description 中行信用卡定时任务，根据不同环境判断是否执行
 * @date 2019-09-03.
 */
public class BOCCCQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(BOCCCQuartz.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {

        /**
         * 根据不同环境，判断此定时任务是否执行
         */
        String[] BOCCC_ENVIRONMENT = {"dev", "boccc", "boccctest"};

        if (!Arrays.asList(BOCCC_ENVIRONMENT).contains(EnvironmentConstant.ENVIRONMENT)) {
            return;
        }

        logger.info("定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务开始执行");

//        //定时生成商户信息
//        try {
//            logger.info("生成商户信息");
//            AutoMerchantInfoUtils.generateFile();
//            logger.info("生成商户信息完成");
//        } catch (Exception e) {
//            logger.error("生成商户信息失败，异常信息：" + e.getMessage());
//        }
//
//        //定时生成商品信息
//        try {
//            logger.info("生成商品信息");
//            AutoProductInfoUtils.generateFile();
//            logger.info("生成商品信息完成");
//        } catch (Exception e) {
//            logger.error("生成商品信息失败，异常信息：" + e.getMessage());
//        }
//
//        //定时生成优惠券信息
//        try {
//            logger.info("生成优惠券");
//            AutoCouponUtils.generateFile();
//            logger.info("生成优惠券完成");
//        } catch (Exception e) {
//            logger.error("生成优惠券失败，异常信息：" + e.getMessage());
//        }
//
//        //定时生成图片信息
//        try {
//            logger.info("生成图片");
//            AutoPicUtils.generateFile();
//            logger.info("生成图片完成");
//        } catch (Exception e) {
//            logger.error("生成图片失败，异常信息：" + e.getMessage());
//        }

//        //模拟中行生成退货信息
//        try {
//            logger.info("模拟生成中行退货信息");
//            AutoCCancelInfoUtils.generateFile();
//            logger.info("模拟生成中行退货信息完成");
//        } catch (Exception e) {
//            logger.error("模拟生成中行退货信息失败，异常信息：" + e.getMessage());
//        }

        //定时下载文件（退货信息文件，每日优惠券剩余数量文件，每日商品购买清单文件）
//        try {
//            logger.info("定时任务：下载文件执行中");
//            AutoSFTPUtils.download();
//            logger.info("定时任务：下载文件执行完成");
//        } catch (Exception e) {
//            logger.info("定时任务：下载文件失败，异常信息：" + e.getMessage());
//        }

//        //定时分析退货信息，生成退货信息结果至上传文件夹
//        try {
//            logger.info("定时任务：分析退货信息执行中");
//            AutoCCancelResultUtils.generateFile();
//            logger.info("定时任务：分析退货信息执行完成");
//        } catch (Exception e) {
//            logger.error("定时任务：分析退货信息执行失败，异常信息：" + e.getMessage());
//        }

        //定时生成前一日的使用情况
        try {
            logger.info("定时任务：前一日优惠券使用情况文件生成中");
            AutoCouponUsedUtils.generateFile();
            logger.info("定时任务：前一日优惠券使用情况文件生成完成");
        } catch (Exception e) {
            logger.error("定时任务：前一日优惠券使用情况文件生成失败，异常信息：" + e.getMessage());
        }

        //定时上传指定文件夹的文件
        try {
            logger.info("定时任务：上传文件执行中");
            AutoSFTPUtils.upload();
            logger.info("定时任务：上传文件执行完成");
        } catch (Exception e) {
            logger.error("定时任务：上传文件执行失败，异常信息：" + e.getMessage());
        }

//        //定时分析前一日优惠券剩余数量
//        try {
//            logger.info("定时任务：分析前一日优惠券剩余数量");
//            AutoDailyRemainAnalysisUtils.generateFile();
//            logger.info("定时任务：前一日优惠券剩余数量分析完成");
//        } catch (Exception e) {
//            logger.error("定时任务：前一日优惠券剩余数量分析失败，异常信息：" + e.getMessage());
//        }
//
//        //定时分析前一日商品购买清单
//        try {
//            logger.info("定时任务：分析前一日商品购买数量");
//            AutoDailyPurchaseAnalysisUtils.generateFile();
//            logger.info("定时任务：前一日商品购买数量分析完成");
//        } catch (Exception e) {
//            logger.error("定时任务：前一日商品购买数量分析失败，异常信息：" + e.getMessage());
//        }
        logger.info("定时任务：当前环境为：" + EnvironmentConstant.ENVIRONMENT + "，定时任务执行完成");
    }
}
