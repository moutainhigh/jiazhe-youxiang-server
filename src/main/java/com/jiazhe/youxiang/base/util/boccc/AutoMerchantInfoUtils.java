package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author TU
 * @description 商户信息工具类
 * @date 2019-09-03.
 */
public class AutoMerchantInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoMerchantInfoUtils.class);

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        //判断今日是否有商户excel文件，有则根据excel生成，无则直接生成文件尾
        String merchantExcelUrl = BOCCCConstant.merchantPath + BOCCCUtils.getToday() + ".xlsx";
        File merchantExcel = new File(merchantExcelUrl);
        if (merchantExcel.exists()) {
            Sheet sheet = ExcelUtils.excel2Sheet(merchantExcelUrl);
            int count = 0;
            for (Row row : sheet) {
                //跳过表头
                if (count == 0) {
                    count++;
                } else {
                    if (!ExcelUtils.getStringValue(row.getCell(0)).equals("")) {
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(1)), ' ', false, 10)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(2)), ' ', false, 6)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(3)), ' ', false, 6)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(4)), ' ', false, 6)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(5)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(6)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(7)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(8)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(9)), ' ', false, 20)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(10)), ' ', false, 100)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(11)), ' ', false, 100)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(12)), ' ', false, 50)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(13)), ' ', false, 50)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(14)), ' ', false, 10)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(15)), ' ', false, 1)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(16)), ' ', false, 15)).append(BOCCCConstant.BOC_Separator);
                        sb.append("").append(BOCCCConstant.BOC_Separator);
                        sb.append("").append(BOCCCConstant.BOC_Separator);
                        sb.append("\r\n");
                        count++;
                    }
                }
            }
            //添加文件尾部信息
            sb.append(BOCCCUtils.generateFileEndChar(count - 1));
        } else {
            sb.append(BOCCCUtils.generateFileEndChar(0));
        }
        return sb;
    }

    /**
     * 根据以上信息，生成商户信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        BOCCCUtils.mkDirs(BOCCCConstant.merchantPath + "work/" + BOCCCUtils.getToday());

        String sourceFileName = BOCCCConstant.merchantPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.MER_SOURCE, 0);
        String zipFileName = BOCCCConstant.merchantPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.MER_ZIP, 0);
        String pgpFileName = BOCCCConstant.merchantPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.MER_PGP, 0);

        //第1步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();

        logger.info("商户字符串编码为：" + StringEncodeUtils.getEncoding(sb.toString()));

        //第2步，写入文件中
        logger.info("商户信息源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("商户信息源文件生成完成，路径为：" + sourceFileName);

        //第3步，源文件压缩中
        logger.info("商户信息源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("商户信息源文件压缩完成，路径为：" + zipFileName);

        //第4步，压缩文件加密中
        logger.info("商户信息压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("商户信息压缩文件加密完成，路径为：" + pgpFileName);

        //第5步，复制加密文件至upload里
        BOCCCUtils.copyToUpload(pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
