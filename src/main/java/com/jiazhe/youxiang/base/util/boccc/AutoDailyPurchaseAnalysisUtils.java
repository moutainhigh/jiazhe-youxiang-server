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

/**
 * @author tu
 * @version 1.0
 * @description 每日购买分析
 * @created 2019-09-05 18:50
 */
@Component
public class AutoDailyPurchaseAnalysisUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoDailyPurchaseAnalysisUtils.class);

    public static AutoDailyPurchaseAnalysisUtils dailyPurchaseAnalysisUtils;

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
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.contains("TLRL")) {
                sb.append("\r\n").append("\r\n").append("\r\n");
            } else {
                sb.append(line);
                sb.append("\r\n");
            }
        }
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

        String sourceFileName = BOCCCConstant.dailyPurchase + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_SOURCE, -1);
        String zipFileName = BOCCCConstant.dailyPurchase + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_ZIP, -1);
        String pgpFileName = BOCCCConstant.dailyPurchase + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_PGP, -1);

        //第一步，判断download文件夹中是否有下传的每日购买清单加密文件
        File dailyPurchasePgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_PGP, -1));
        if (dailyPurchasePgpFile.exists()) {
            //如果中行下传了每日购买加密文件，复制到dailypurchase当日文件夹下
            String path = BOCCCConstant.dailyPurchase + BOCCCUtils.getToday();
            BOCCCUtils.mkDirs(path);
            FileUtils.copyFileToDirectory(dailyPurchasePgpFile, new File(path));
        } else {
            logger.info("未收到总行下传的每日购买清单加密文件");
            return;
        }

        //第二步，解密文件
        PgpDecryUtil decryU = new PgpDecryUtil();
        decryU.setPassphrase(EnvironmentConstant.PASSPHRASE);
        decryU.DecryUtil(pgpFileName, zipFileName, BOCCCConstant.privateKeyPath);

        //第三步，解压缩文件
        UnZipUtil.ZipContraFile(zipFileName, BOCCCConstant.dailyPurchase + BOCCCUtils.getToday());

        //第四步,读取文件进行分析，生成 【日期+购买数量】 字符串
        StringBuilder sb = generateBin(sourceFileName);

        //第五步，将当前结果追加到月度购买清单，年度购买清单
        BOCCCUtils.contentAppend(BOCCCConstant.dailyPurchase + "月度购买清单" + BOCCCUtils.getYesterday().substring(0, 4) + ".txt", sb.toString());
        BOCCCUtils.contentAppend(BOCCCConstant.dailyPurchase + "年度购买清单" + BOCCCUtils.getYesterday().substring(0, 2) + ".txt", sb.toString());

    }
}
