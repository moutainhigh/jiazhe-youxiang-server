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

        //第一步，判断download文件夹中是否有下传的退货信息加密文件,
        File BOCCCancelPgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_PGP, -1));
        if (BOCCCancelPgpFile.exists()) {
            //如果中行下传了每日优惠券剩余加密文件，复制到dailyremain当日文件夹下
            File file = new File(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getToday());
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
        decryU.setPassphrase(EnvironmentConstant.PASSPHRASE);
        decryU.DecryUtil(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_PGP, -1), BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_ZIP, -1), BOCCCConstant.privateKeyPath);

        //第三步，解压缩文件
        UnZipUtil.ZipContraFile(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_ZIP, -1), BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday());

        //第四步,读取文件进行分析生成源文件字符串
        StringBuilder sb = generateBin(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CREMA_SOURCE, -1));

        //第五步，将当前结果追加到月份文件中
        BOCCCUtils.contentAppend(BOCCCConstant.rootPath + "dailyremain/" + BOCCCUtils.getYesterday() + "剩余总清单.txt", sb.toString());

    }

}
