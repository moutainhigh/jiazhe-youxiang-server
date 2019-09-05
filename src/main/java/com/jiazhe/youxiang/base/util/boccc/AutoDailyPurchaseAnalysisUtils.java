/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
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
    private VoucherExchangeCodeService voucherExchangeCodeService;

    @PostConstruct
    public void init() {
        dailyPurchaseAnalysisUtils = this;
        dailyPurchaseAnalysisUtils.voucherExchangeCodeService = this.voucherExchangeCodeService;
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
    public static void generateFile() throws Exception {

        //第一步，判断download文件夹中是否有下传的退货信息加密文件,
        File BOCCCancelPgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_PGP, -1));
        if (BOCCCancelPgpFile.exists()) {
            //如果中行下传了每日购买加密文件，复制到dailypurchase当日文件夹下
            File file = new File(BOCCCConstant.rootPath + "dailypurchase/" + BOCCCUtils.getToday());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileUtils.copyFileToDirectory(BOCCCancelPgpFile, file);
        } else {
            logger.info("未收到总行发给第三方每日购买文件");
            return;
        }

        //第二步，解密文件
        PgpDecryUtil decryU = new PgpDecryUtil();
        decryU.setPassphrase(EnvironmentConstants.PASSPHRASE);
        decryU.DecryUtil(BOCCCConstant.rootPath + "dailypurchase/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_PGP, -1), BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_ZIP, -1), BOCCCConstant.privateKeyPath);

        //第三步，解压缩文件
        UnZipUtil.ZipContraFile(BOCCCConstant.rootPath + "dailypurchase/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_ZIP, -1), BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday());

        //第四步,读取文件进行分析生成源文件字符串
        StringBuilder sb = generateBin(BOCCCConstant.rootPath + "dailypurchase/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CSELL_SOURCE, -1));

        //第五步，将当前结果追加到月份文件中
        BOCCCUtils.contentAppend(BOCCCConstant.rootPath + "dailypurchase/" + BOCCCUtils.getYesterday() + ".txt",sb.toString());

    }
}
