package com.jiazhe.youxiang.server.quartz;

import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author TU
 * @description 中行信用卡定时任务，根据不同环境判断是否执行
 * @date 2019-09-03.
 */
public class BOCCCQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(BOCCCQuartz.class);

    @Value("${spring.profiles.active}")
    private String ENVIRONMENT;

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {

        /**
         * 根据不同环境，判断此定时任务是否执行
         */
        switch (ENVIRONMENT) {
            case "xls":
                return;
            case "demo":
                return;
        }

        logger.info("定时任务：当前环境为：" + ENVIRONMENT + "，定时任务开始执行");

        String content = "{" +"\"orderId\": \"01\"," + "\"waresId\": \"01\"," + "\"wInfo\": \"120088644437\"," + "\"returnDate\": \"20190911\"," + "\"returnTime\": \"190000\"" +"}";
//        String publicEncrypt = BOCCCUtils.publicEncrypt(content.trim());
//        logger.info("公钥加密内容：" + publicEncrypt);
//        String privateDecrypt = BOCCCUtils.privateDecrypt(publicEncrypt);
//        logger.info("私钥解密内容：" + privateDecrypt);

        String privateEncrypt = BOCCCUtils.privateEncrypt(content);
        logger.info("私钥加密内容：" + privateEncrypt);
        String publicDecrypt = BOCCCUtils.publicDecrypt(privateEncrypt);
        logger.info("公钥解密内容：" + publicDecrypt);


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
//
//        //定时分析退货信息，生成退货信息结果至上传文件夹
//        try {
//            logger.info("定时任务：分析退货信息执行中");
//            AutoCCancelResultUtils.generateFile();
//            logger.info("定时任务：分析退货信息执行完成");
//        } catch (Exception e) {
//            logger.error("定时任务：分析退货信息执行失败，异常信息：" + e.getMessage());
//        }
//
//        //定时生成前一日的使用情况
//        try {
//            logger.info("定时任务：前一日优惠券使用情况文件生成中");
//            AutoCouponUsedUtils.generateFile();
//            logger.info("定时任务：前一日优惠券使用情况文件生成完成");
//        } catch (Exception e) {
//            logger.error("定时任务：前一日优惠券使用情况文件生成失败，异常信息：" + e.getMessage());
//        }
//
//        //定时上传指定文件夹的文件
//        try {
//            logger.info("定时任务：上传文件执行中");
//            AutoSFTPUtils.upload();
//            logger.info("定时任务：上传文件执行完成");
//        } catch (Exception e) {
//            logger.error("定时任务：上传文件执行失败，异常信息：" + e.getMessage());
//        }
//
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
        logger.info("定时任务：当前环境为：" + ENVIRONMENT + "，定时任务执行完成");
    }
}
