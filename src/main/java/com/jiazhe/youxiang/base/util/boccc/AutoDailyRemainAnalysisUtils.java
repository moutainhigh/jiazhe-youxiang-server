/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tu
 * @version 1.0
 * @description 每日剩余分析
 * @created 2019-09-05 18:52
 */
@Component
public class AutoDailyRemainAnalysisUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoDailyRemainAnalysisUtils.class);

    public static AutoDailyRemainAnalysisUtils dailyPurchaseAnalysisUtils;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    @PostConstruct
    public void init() {
        dailyPurchaseAnalysisUtils = this;
        dailyPurchaseAnalysisUtils.pointExchangeCodeService = this.pointExchangeCodeService;
    }

    public static StringBuilder generateBin(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder("------------------------" + BOCCCUtils.getYesterday() + "------------------------").append("\r\n");
        Map<String, Integer> map = new HashMap<>();
        String line = "";
        String productId = "";
        Integer count = 0;
        Integer used = 0;
        Integer left = 0;
        Integer totalCount = 0;
        Integer totalUsed = 0;
        Integer totalLeft = 0;
        while ((line = br.readLine()) != null) {
            if (BOCCCUtils.isLastLine(line)) {
                logger.info("每日剩余优惠券文件读取完成");
            } else {
                productId = line.substring(0, 11);
                count = Integer.valueOf(line.substring(22, 30).trim());
                totalCount += count;
                used = Integer.valueOf(line.substring(35, 43).trim());
                totalUsed += used;
                left = Integer.valueOf(line.substring(48, 56).trim());
                totalLeft += left;
                sb.append(productId + " 总数量：" + count + "个； 总使用：" + used + "个； 总剩余：" + left + "个；").append("\r\n");
            }
        }
        sb.append("--------总数量：" + totalCount + "个； 总使用：" + totalUsed + "个； 总剩余：" + totalLeft + "个；--------");
        sb.append("\r\n").append("\r\n").append("\r\n");
        br.close();
        isr.close();
        fis.close();
        return sb;
    }

    /**
     * 根据以上信息，生成每日购买分析文件，不加密
     *
     * @return
     */
    @Deprecated
    public static void generateFile() throws Exception {

        //第一步，判断download文件夹中是否有下传的退货信息加密文件,
        File BOCCCancelPgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_PGP, -1));
        if (BOCCCancelPgpFile.exists()) {
            //如果中行下传了每日优惠券剩余加密文件，复制到dailyremain当日文件夹下
            File file = new File(BOCCCConstant.dailyRemain + BOCCCUtils.getToday());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileUtils.copyFileToDirectory(BOCCCancelPgpFile, file);
        } else {
            logger.info("未收到总行下传的每日优惠券剩余加密文件");
            return;
        }

        //第二步，解密文件
        PgpDecryUtil decryU = new PgpDecryUtil();
        decryU.setPassphrase(BOCCCUtils.PASSPHRASE);
        decryU.DecryUtil(BOCCCConstant.dailyRemain + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_PGP, -1), BOCCCConstant.dailyRemain + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_ZIP, -1), BOCCCConstant.privateKeyPath);

        //第三步，解压缩文件
        UnZipUtil.ZipContraFile(BOCCCConstant.dailyRemain + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_ZIP, -1), BOCCCConstant.dailyRemain + BOCCCUtils.getToday());

        //第四步,读取文件进行分析生成源文件字符串
        StringBuilder sb = generateBin(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_SOURCE, -1));

        //第五步，将当前结果追加到月度剩余清单，年度剩余清单
        BOCCCUtils.contentAppend(BOCCCConstant.dailyRemain + "月度剩余清单" + BOCCCUtils.getYesterday().substring(0, 6) + ".txt", sb.toString());
        BOCCCUtils.contentAppend(BOCCCConstant.dailyRemain + "年度剩余清单" + BOCCCUtils.getYesterday().substring(0, 4) + ".txt", sb.toString());

    }

}
