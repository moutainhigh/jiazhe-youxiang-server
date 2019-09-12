package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author TU
 * @description 商品信息工具类
 * @date 2019-09-02.
 */
public class AutoProductInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoProductInfoUtils.class);

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        //判断今日是否有商品excel文件，有则根据excel生成，无则直接生成文件尾
        String productExcelUrl = BOCCCConstant.productPath + BOCCCUtils.getToday() + ".xlsx";
        File merchantExcel = new File(productExcelUrl);
        if (merchantExcel.exists()) {
            Sheet sheet = ExcelUtils.excel2Sheet(productExcelUrl);
            int count = 0;
            for (Row row : sheet) {
                //跳过表头
                if (count == 0) {
                    count++;
                } else {
                    if (!ExcelUtils.getStringValue(row.getCell(0)).equals("")) {
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(1)), ' ', false, 11)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(2)), ' ', false, 40)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(3)), ' ', false, 2)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(4)), ' ', false, 4)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(5)), ' ', false, 10)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(6)), ' ', true, 11)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(7)), ' ', true, 11)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(8)), ' ', false, 10)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(9)), ' ', false, 10)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(10)), ' ', false, 1)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(11)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(12)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(13)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(14)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(15)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(16)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(17)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(18)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(19)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append(BOCCCUtils.complete(ExcelUtils.getStringValue(row.getCell(20)), ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                        sb.append("").append(BOCCCConstant.BOC_Separator);
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
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        BOCCCUtils.mkDirs(BOCCCConstant.productPath + "work/" + BOCCCUtils.getToday());

        String sourceFileName = BOCCCConstant.productPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.WARES_SOURCE, 0);
        String zipFileName = BOCCCConstant.productPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.WARES_ZIP, 0);
        String pgpFileName = BOCCCConstant.productPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.WARES_PGP, 0);

        //第1步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();

        //第2步，写入文件中
        logger.info("商品信息源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("商品信息源文件生成完成，路径为：" + sourceFileName);

        //第3步，源文件压缩中
        logger.info("商品信息源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("商品信息源文件压缩完成，路径为：" + zipFileName);

        //第4步，压缩文件加密中
        logger.info("商品信息压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("商品信息压缩文件加密完成，路径为：" + pgpFileName);

        //第5步，复制加密文件至upload里
        BOCCCUtils.copyToUpload(pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
