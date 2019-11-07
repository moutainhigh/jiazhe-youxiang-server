/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author tu
 * @version 1.0
 * @description 商品、商户图片加密
 * @created 2019-09-05 18:39
 */
public class AutoPicUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoPicUtils.class);

    /**
     * 生成压缩图片文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        BOCCCUtils.mkDirs(BOCCCConstant.picPath + "work/" + BOCCCUtils.getToday());

        //存放手动上传进来的png文件的文件夹
        String todayPicPath = BOCCCConstant.picPath + BOCCCUtils.getToday();

        //下级文件夹boc中，存放每日生成的压缩、加密文件
        String zipFileName = BOCCCConstant.picPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.PIC_ZIP, 0);
        String pgpFileName = BOCCCConstant.picPath + "work/" + BOCCCUtils.getToday() + "/" + BOCCCUtils.getFileName(BOCCCConstant.PIC_PGP, 0);

        //第1步，判断路径是否存在，存在则压缩路径下所有文件，不存在则压缩一个空文件
        File file = new File(todayPicPath);
        if (file.exists()) {
            logger.info("图片文件源文件压缩中...");
            new ZipUtil(new File(zipFileName)).zipFiles(new File(todayPicPath));
            logger.info("图片文件源文件压缩完成，路径为：" + zipFileName);
        } else {
            logger.info("图片空文件压缩中...");
            new ZipUtil(new File(zipFileName)).zipFiles(new File(""));
            logger.info("图片空文件压缩完成...");
        }

        //第2步，压缩文件加密中
        logger.info("图片文件压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("图片文件压缩文件加密完成，路径为：" + pgpFileName);

        //第3步，复制加密文件至upload里
        BOCCCUtils.copyToUpload(pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
