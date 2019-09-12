package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TU
 * @description 优惠券信息工具类
 * @date 2019-09-03.
 */
@Component
public class AutoCouponUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoCouponUtils.class);

    public static AutoCouponUtils couponUtils;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    @PostConstruct
    public void init() {
        couponUtils = this;
        couponUtils.pointExchangeCodeService = this.pointExchangeCodeService;
    }

    public static StringBuilder generateBin() throws Exception {
        List<Integer> batchIds = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        //判断今日是否有优惠券excel文件，有则根据excel生成，无则直接生成文件尾
        String productExcelUrl = BOCCCConstant.couponPath + BOCCCUtils.getToday() + ".xlsx";
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
                        batchIds.add(Integer.valueOf(ExcelUtils.getStringValue(row.getCell(2)).trim()));
                        count++;
                    }
                }
            }
            int couponCount = 0;
            List<BOCCCCouponEntity> list = couponUtils.pointExchangeCodeService.getBOCCCCoupon(batchIds);
            for (BOCCCCouponEntity entity : list) {
                couponCount++;
                sb.append(entity.getGiftNo()).append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(entity.getId(), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
                sb.append("R").append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(entity.getKeyt(), ' ', false, 36)).append(BOCCCConstant.BOC_Separator);
                //预留字段还未拼接
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("\r\n");
            }
            //添加文件尾部信息
            sb.append(BOCCCUtils.generateFileEndChar(couponCount));
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

        BOCCCUtils.mkDirs(BOCCCConstant.couponPath + BOCCCUtils.getToday());

        String sourceFileName = BOCCCConstant.couponPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.COUPON_SOURCE, 0);
        String zipFileName = BOCCCConstant.couponPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.COUPON_ZIP, 0);
        String pgpFileName = BOCCCConstant.couponPath + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.COUPON_PGP, 0);

        //第1步，按照规则组成优惠券字符串
        StringBuilder sb = generateBin();

        //第2步，写入文件中
        logger.info("优惠券源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("优惠券源文件生成完成，路径为：" + sourceFileName);

        //第3步，源文件压缩中
        logger.info("优惠券源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("优惠券源文件压缩完成，路径为：" + zipFileName);

        //第4步，压缩文件加密中
        logger.info("优惠券压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("优惠券压缩文件加密完成，路径为：" + pgpFileName);

        //第5步，复制加密文件至upload里
        BOCCCUtils.copyToUpload(pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }
}
