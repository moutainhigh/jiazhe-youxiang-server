package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
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
 * @author TU
 * @description 分析中行下传过来的退货信息，并生成结果上传
 * @date 2019-09-05.
 */
@Component
public class AutoCCancelResultUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoCCancelResultUtils.class);

    public static AutoCCancelResultUtils cCancelResultUtils;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    @PostConstruct
    public void init() {
        cCancelResultUtils = this;
        cCancelResultUtils.pointExchangeCodeService = this.pointExchangeCodeService;
    }

    public static StringBuilder generateBin(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.contains("TLRL")) {
                sb.append(line);
            } else {
                sb.append(cancelResult(line));
                sb.append("\r\n");
            }
        }
        br.close();
        isr.close();
        fis.close();
        return sb;
    }

    /**
     * 根据以上信息，生成退货信息反馈结果
     *
     * @return
     */
    @Deprecated
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BJYX_CCANCEL_SOURCE, 0);
        String zipFileName = BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BJYX_CCANCEL_ZIP, 0);
        String pgpFileName = BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BJYX_CCANCEL_PGP, 0);

        //第1步，判断download文件夹中是否有下传的退货信息加密文件,
        File BOCCCancelPgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_PGP, -1));
        if (BOCCCancelPgpFile.exists()) {
            //如果中行下传了加密文件，复制到ccancel当日文件夹下
            File file = new File(BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileUtils.copyFileToDirectory(BOCCCancelPgpFile, file);
        } else {
            logger.info("未收到总行下传的每日退货加密文件");
            return;
        }

        //第2步，解密文件
        PgpDecryUtil decryU = new PgpDecryUtil();
        decryU.setPassphrase(EnvironmentConstant.PASSPHRASE);
        decryU.DecryUtil(BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_PGP, -1), BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_ZIP, -1), BOCCCConstant.privateKeyPath);

        //第3步，解压缩文件
        UnZipUtil.ZipContraFile(BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_ZIP, -1), BOCCCConstant.ccancelPath + BOCCCUtils.getToday());

        //第4步,读取文件进行分析生成源文件字符串
        StringBuilder sb = generateBin(BOCCCConstant.ccancelPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_SOURCE, -1));

        //第5步，源文件压缩中
        logger.info("退货结果源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("退货结果源文件生成完成，路径为：" + sourceFileName);

        //第6步，源文件压缩中
        logger.info("退货结果源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("退货结果源文件压缩完成，路径为：" + zipFileName);

        //第7步，压缩文件加密中
        logger.info("退货结果压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("退货结果压缩文件加密完成，路径为：" + pgpFileName);

        //第8步，将文件放复制至upload文件夹中
        BOCCCUtils.copyToUpload(pgpFileName);
    }

    public static String cancelResult(String cancelStr) throws Exception {
        StringBuilder sb = new StringBuilder(cancelStr.substring(0, 102));
        String keyt = cancelStr.substring(61, 97);
        String failureDesc = "";
        String cancelStatus = "Y";
        PointExchangeCodePO po = cCancelResultUtils.pointExchangeCodeService.findByKeyt(keyt.trim());
        if (null == po) {
            cancelStatus = "N";
            failureDesc = "优惠券码不存在";
        } else {
            if (po.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                cancelStatus = "N";
                failureDesc = "优惠券码已经使用";
            }
        }
        sb.append(cancelStatus).append(cancelStr.substring(103, 134)).append(BOCCCUtils.complete(failureDesc, ' ', false, 200)).append(cancelStr.substring(334, cancelStr.length()));
        return sb.toString();
    }
}
