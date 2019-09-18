/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
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
 * @description 优惠券昨日已使用，定时自动执行
 * @created 2019-09-03 20:24
 */
@Component
public class AutoCouponUsedUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoCouponUsedUtils.class);

    public static AutoCouponUsedUtils couponUsedUtils;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    @PostConstruct
    public void init() {
        couponUsedUtils = this;
        couponUsedUtils.pointExchangeCodeService = this.pointExchangeCodeService;
    }

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        List<BOCCCCouponUsedEntity> list = couponUsedUtils.pointExchangeCodeService.getBOCCCYesterdayUsed();
        if (list.isEmpty()) {
            sb.append(BOCCCUtils.generateFileEndChar(0));
        } else {
            for (BOCCCCouponUsedEntity used : list) {
                sb.append(BOCCCUtils.complete(used.getGiftNo(), ' ', false, 11)).append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(String.valueOf(used.getId()), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
                sb.append("R").append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(used.getKeyt(), ' ', false, 36)).append(BOCCCConstant.BOC_Separator);
                sb.append(DateUtil.yyyyMMDD(used.getUsedTime())).append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCConstant.SHOP_ID).append(BOCCCConstant.BOC_Separator);
                //预留字段还未拼接
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("\r\n");
            }
            sb.append(BOCCCUtils.generateFileEndChar(list.size()));
        }

        return sb;
    }

    /**
     * 根据以上信息，生成昨日已使用优惠券加密压缩文件
     *
     * @return
     */
    @Deprecated
    public static void generateFile() throws Exception {

        BOCCCUtils.mkDirs(BOCCCConstant.cusedPath + "work/" + BOCCCUtils.getToday());

        String sourceFileName = BOCCCConstant.cusedPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CUSED_SOURCE, 0);
        String zipFileName = BOCCCConstant.cusedPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CUSED_ZIP, 0);
        String pgpFileName = BOCCCConstant.cusedPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.CUSED_PGP, 0);

        //第1步，按照规则拼接已使用代金券信息
        StringBuilder sb = generateBin();

        logger.info("已使用优惠券字符串编码为：" + StringEncodeUtils.getEncoding(sb.toString()));

        //第2步，写入文件中
        logger.info("昨日使用代金券源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("昨日使用代金券源文件生成完成，路径为：" + sourceFileName);

        //第3步，源文件压缩中
        logger.info("昨日使用代金券源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("昨日使用代金券源文件压缩完成，路径为：" + zipFileName);

        //第4步，压缩文件加密中
        logger.info("昨日使用代金券压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("昨日使用代金券压缩文件加密完成，路径为：" + pgpFileName);

        //第5步，将加密文件复制到待上传的文件夹中
        BOCCCUtils.copyToUpload(pgpFileName);
    }
}
