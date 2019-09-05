package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
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
 * @author TU
 * @description 分析中行下传过来的退货信息，并生成结果上传
 * @date 2019-09-05.
 */
@Component
public class CCancelResultUtils {

    public static Logger logger = LoggerFactory.getLogger(CCancelResultUtils.class);

    public static CCancelResultUtils cCancelResultUtils;

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    /**
     * 中行下传的退货信息文件名，昨日文件
     */
    private final static String cCancelInfoPgpFileName = "CCANCEL.BOC" + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getYesterday() + ".00.ZIP.DAT";
    private final static String cCancelInfoZipFileName = "CCANCEL.BOC" + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getYesterday() + ".00.ZIP";
    private final static String cCancelInfoSourceFileName = "CCANCEL.BOC" + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getYesterday() + ".00.C";

    @PostConstruct
    public void init() {
        cCancelResultUtils = this;
        cCancelResultUtils.voucherExchangeCodeService = this.voucherExchangeCodeService;
    }

    public static StringBuilder generateBin(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.contains("TLRL")) {
                sb.append(cancelResult(line));
                sb.append("\r\n");
            } else {
                sb.append(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return sb;
    }

    /**
     * 源文件的文件路径
     *
     * @return
     */
    public static String generateSourceFileName() {
        return "CCANCEL." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.C";
    }

    /**
     * 压缩文件的文件路径
     *
     * @return
     */
    public static String generateZipFileName() {
        return "CCANCEL." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.C.ZIP";
    }

    /**
     * 加密文件文件路径
     *
     * @return
     */
    public static String generatePgpFileName() {
        return "CCANCEL." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.C.ZIP.DAT";
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + generateSourceFileName();
        String zipFileName = BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + generateZipFileName();
        String pgpFileName = BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + generatePgpFileName();

        //第一步，判断download文件夹中是否有下传的退货信息加密文件,
        File BOCCCancelPgpFile = new File(BOCCCConstant.downloadPath + BOCCCUtils.getToday() + "/" + cCancelInfoPgpFileName);
        if (BOCCCancelPgpFile.exists()) {
            //如果中行下传了加密文件，复制到ccancel当日文件夹下
            File file = new File(BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileUtils.copyFileToDirectory(BOCCCancelPgpFile, file);
        } else {
            logger.info("未收到总行发给第三方退货信息");
            return;
        }

        //第二步，解密文件
        PgpDecryUtil decryU = new PgpDecryUtil();
        decryU.setPassphrase("youxianghulian0612");
        decryU.DecryUtil(BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + cCancelInfoPgpFileName, BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + cCancelInfoZipFileName, BOCCCConstant.privateKeyPath);

        //第三步，解压缩文件
        UnZipUtil.ZipContraFile(BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + cCancelInfoZipFileName, BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday());

        //第四步,读取文件进行分析生成源文件字符串
        StringBuilder sb = generateBin(BOCCCConstant.rootPath + "ccancel/" + BOCCCUtils.getToday() + "/" + cCancelInfoSourceFileName);

        //第五步，源文件压缩中
        logger.info("源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("源文件生成完成，路径为：" + sourceFileName);

        //第六步，源文件压缩中
        logger.info("源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("源文件压缩完成，路径为：" + zipFileName);

        //第七步，压缩文件加密中
        logger.info("压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("压缩文件加密完成，路径为：" + pgpFileName);

        //第八步，将文件放复制至upload文件夹中
        String uploadPath = BOCCCConstant.uploadPath + BOCCCUtils.getToday();
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyFileToDirectory(new File(pgpFileName), new File(uploadPath));

    }

    public static String cancelResult(String cancelStr) throws Exception {
        StringBuilder sb = new StringBuilder(cancelStr.substring(0, 102));
        String keyt = cancelStr.substring(61, 97);
        String failureDesc = "";
        String cancelStatus = "Y";
        VoucherExchangeCodePO po = cCancelResultUtils.voucherExchangeCodeService.findByKeyt(keyt);
        if (null == po) {
            sb.append("N");
            failureDesc = "优惠券码不存在";
        } else {
            if (po.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                sb.append("N");
                failureDesc = "优惠券码已经使用";
            }
        }
        sb.append(cancelStatus).append(cancelStr.substring(103, 134)).append(BOCCCUtils.complete(failureDesc, ' ', false, 200)).append(cancelStr.substring(334, 354));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }
}
