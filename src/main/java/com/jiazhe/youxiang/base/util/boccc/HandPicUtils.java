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
public class HandPicUtils {

    public static Logger logger = LoggerFactory.getLogger(HandPicUtils.class);


    /**
     * 生成压缩图片文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径 day=-1表示昨日文件  0表示今日文件   1表示明日文件
        int day = 1;
        String zipFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.PIC_ZIP, day);
        String pgpFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.PIC_PGP, day);


        //第1步，压缩路径下所有文件
        logger.info("图片文件源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipDirectory(sourceFile);
        logger.info("图片文件源文件压缩完成，路径为：" + zipFileName);

        //第2步，压缩文件加密中
        logger.info("图片文件压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("图片文件压缩文件加密完成，路径为：" + pgpFileName);
    }


    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
