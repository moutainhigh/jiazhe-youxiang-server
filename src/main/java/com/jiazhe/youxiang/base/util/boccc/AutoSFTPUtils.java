/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/**
 * @author tu
 * @version 1.0
 * @description SFTP工具类，定时自动下载、上传
 * @created 2019-09-03 20:53
 */
@Component
public class AutoSFTPUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoSFTPUtils.class);

    private ChannelSftp sftp;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private static String username;
    /**
     * SFTP 登录密码
     */
    private static String password;
    /**
     * 私钥
     */
    private static String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private static String host;
    /**
     * SFTP 端口
     */
    private static int port;

    /**
     * 上传到中行的根路径
     */
    private static String outPath;

    /**
     * 中行下发文件的根路径
     */
    private static String inPath;

    /**
     * 构造基于密码认证的sftp对象
     */
    public AutoSFTPUtils(String USERNAME, String PASSWORD, String HOST, int PORT) {
        username = USERNAME;
        password = PASSWORD;
        host = HOST;
        port = PORT;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public AutoSFTPUtils(String USERNAME, String HOST, int PORT) {
        username = USERNAME;
        host = HOST;
        port = PORT;
        privateKey = BOCCCConstant.loginPrivateKeyPath;
    }

    public AutoSFTPUtils() {
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                // 设置私钥
                jsch.addIdentity(privateKey);
            }
            session = jsch.getSession(username, host, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = basePath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) {
                    continue;
                }
                tempPath += "/" + dir;
                try {
                    sftp.cd(tempPath);
                } catch (SftpException ex) {
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        //上传文件
        sftp.put(input, sftpFileName);
    }

    /**
     * 下载文件，文件名不改变。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param savePath     存在本地的路径
     */
    public void download(String directory, String downloadFile, String savePath) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File localDirectory = new File(savePath);
        if (!localDirectory.exists()) {
            localDirectory.mkdirs();
        }
        File file = new File(savePath + "\\" + downloadFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);
        byte[] fileData = IOUtils.toByteArray(is);
        return fileData;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    public boolean isExistDir(String path, ChannelSftp sftp) {
        boolean isExist = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(path);
            isExist = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isExist = false;
            }
        }
        return isExist;

    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    @Value("${boccc.sftp.username}")
    public void setUsername(String USERNAME) {
        username = USERNAME;
    }

    @Value("${boccc.sftp.password}")
    public void setPassword(String PASSWORD) {
        password = PASSWORD;
    }

    @Value("${boccc.sftp.host}")
    public void setHost(String HOST) {
        host = HOST;
    }

    @Value("${boccc.sftp.port}")
    public void setPort(int PORT) {
        port = PORT;
    }

    @Value("${boccc.sftp.out}")
    public void setOutPath(String OUTPATH) {
        outPath = OUTPATH;
    }

    @Value("${boccc.sftp.in}")
    public void setInPath(String INPATH) {
        inPath = INPATH;
    }

    /**
     * 上传当日文件夹下所有文件至中行服务器
     *
     * @throws SftpException
     * @throws IOException
     */
    public static void upload() throws SftpException, IOException {
        logger.info("上传文件中");
        AutoSFTPUtils sftp = new AutoSFTPUtils(username, host, port);
        sftp.login();
        //本地将要上传的文件夹
        File uploadPath = new File(BOCCCConstant.uploadPath + BOCCCUtils.getToday());
        //中行接收文件路径存在
        if (sftp.isExistDir(outPath, sftp.sftp)) {
            if (uploadPath.exists()) {
                File[] fs = uploadPath.listFiles();
                for (File file : fs) {
                    InputStream is = new FileInputStream(file);
                    //outPath为上传到中行服务器的路径
                    sftp.upload(outPath, "", file.getName(), is);
                }
                sftp.logout();
            }
        }else{
            logger.error("BOCCC-ERROR：中行上传文件夹路径定义有误！！！");
        }
        logger.info("上传文件完成");
    }

    public static void download() throws SftpException, FileNotFoundException {
        logger.info("下载文件中");
        AutoSFTPUtils sftp = new AutoSFTPUtils(username, password, host, port);
        sftp.login();
        //下载到本地服务器的路径
        String downloadPath = BOCCCConstant.downloadPath + BOCCCUtils.getToday();
        if (sftp.isExistDir(inPath, sftp.sftp)) {
            Vector v = sftp.listFiles(inPath);
            Iterator it = v.iterator();
            while (it.hasNext()) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                String fileName = entry.getFilename();
                SftpATTRS attrs = entry.getAttrs();
                if (!attrs.isDir() && fileName.contains(BOCCCUtils.getYesterday())) {
                    sftp.download(inPath, entry.getFilename(), downloadPath);
                }
            }
            sftp.logout();
        }else{
            logger.error("BOCCC-ERROR：中行下载文件存放路径定义有误！！！");
        }
        logger.info("下载文件完成");
    }

}
