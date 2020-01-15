/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * 文件相关工具类
 *
 * @author niexiao
 * @created 2019-12-03
 */
public class FileUtil {

    /**
     * 判断路径是否存在，不存在则生成
     *
     * @param path
     */
    public static void mkDirs(String path) {
        File usedPath = new File(path);
        if (!usedPath.exists()) {
            usedPath.mkdirs();
        }
    }

    /**
     * 将文件拷贝到指定文件夹
     *
     * @param fileName 指定文件
     * @throws Exception
     */
    public static void copyToPath(String fileName, String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyFileToDirectory(new File(fileName), new File(path));
    }
}
