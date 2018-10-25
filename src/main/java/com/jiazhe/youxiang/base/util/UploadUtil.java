/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/21
 */
public class UploadUtil {

    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @param path          文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String uploadImage(MultipartFile multipartFile, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = "";

        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            fileName = createFileName();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();

        } catch (IOException e) {
//TODO niexiao 抛出自定义异常
        }

        return fileName;
    }

    private static String createFileName() {
        //TODO niexiao 完善
        return "";
    }
}