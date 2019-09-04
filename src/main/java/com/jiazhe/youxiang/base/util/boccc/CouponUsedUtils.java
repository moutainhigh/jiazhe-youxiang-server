/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;


/**
 * @author tu
 * @version 1.0
 * @description 优惠券已使用工具类
 * @created 2019-09-03 20:24
 */
@Component
public class CouponUsedUtils {

    public static Logger logger = LoggerFactory.getLogger(CouponUsedUtils.class);

    public static CouponUsedUtils couponUsedUtils;

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    @PostConstruct
    public void init() {
        couponUsedUtils = this;
        couponUsedUtils.voucherExchangeCodeService = this.voucherExchangeCodeService;
    }

    /**
     * 获取昨日中行信用卡对接已经使用的代金券兑换码
     *
     * @param type 统计对接哪种类型使用情况  1中行信用卡对接
     * @return
     */
    public static List<VoucherExchangeCodeDTO> getYesterdayUsed(String type) {
        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList = couponUsedUtils.voucherExchangeCodeService.getYesterdayUsed(type);
        return voucherExchangeCodeDTOList;
    }

    public static StringBuilder generateBin(List<VoucherExchangeCodeDTO> list) throws Exception {

        StringBuilder sb = new StringBuilder();
        for (VoucherExchangeCodeDTO dto : list) {
            sb.append(dto.getBocccProductId()).append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(String.valueOf(dto.getId()), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
            sb.append("E").append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(dto.getKeyt(), '0', true, 36)).append(BOCCCConstant.BOC_Separator);
            sb.append(DateUtil.yyyyMMDD(dto.getUsedTime())).append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCConstant.MERCHANT_ID).append(BOCCCConstant.BOC_Separator);
            //预留字段还未拼接 TODO
            //TODO
            //换行
            sb.append("\r\n");
        }
        //添加文件尾部信息
        sb.append(BOCCCUtils.generateFileEndChar(list.size()));
        return sb;
    }

    /**
     * 源文件的文件路径
     *
     * @return
     */
    public static String generateSourceFileName() {
        return "CUSED." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.U";
    }

    /**
     * 压缩文件的文件路径
     *
     * @return
     */
    public static String generateZipFileName() {
        return "CUSED." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.U.ZIP";
    }

    /**
     * 加密文件文件路径
     *
     * @return
     */
    public static String generatePgpFileName() {
        return "CUSED." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.U.ZIP.DAT";
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.rootPath + generateSourceFileName();
        String zipFileName = BOCCCConstant.rootPath + generateZipFileName();
        String pgpFileName = BOCCCConstant.rootPath + generatePgpFileName();

        //第一步，获取昨日使用的代金券兑换码
        List<VoucherExchangeCodeDTO> list = getYesterdayUsed("1");

        //第二步，按照规则组成已经使用的代金券兑换码字符串
        if (list.isEmpty()) {
            logger.info("昨日使用代金券数量为：0");
            return;
        }
        logger.info("昨日使用代金券数量为：" + list.size());
        StringBuilder sb = generateBin(list);

        //第三步，写入文件中
        logger.info("源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("源文件生成完成，路径为：" + sourceFileName);

        //第四步，源文件压缩中
        logger.info("源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("源文件压缩完成，路径为：" + zipFileName);

        //第五步，压缩文件加密中
        logger.info("压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("压缩文件加密完成，路径为：" + pgpFileName);

        //第六步，将加密文件复制到待上传的文件夹中
        String uploadPath = BOCCCConstant.uploadPath + BOCCCUtils.getToday();
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyFileToDirectory(new File(pgpFileName), new File(uploadPath));
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }


}
