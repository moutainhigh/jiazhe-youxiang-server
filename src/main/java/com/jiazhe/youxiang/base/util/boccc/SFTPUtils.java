/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;
import org.apache.commons.io.IOUtils;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tu
 * @version 1.0
 * @description SFTP工具类
 * @created 2019-09-03 20:53
 */
@Component
public class SFTPUtils {

    public static Logger logger = LoggerFactory.getLogger(SFTPUtils.class);

    private ChannelSftp sftp;

    private Session session;
    /** SFTP 登录用户名*/
    private static String username;
    /** SFTP 登录密码*/
    private static String password;
    /** 私钥 */
    private static String privateKey;
    /** SFTP 服务器地址IP地址*/
    private static String host;
    /** SFTP 端口*/
    private static int port;


    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFTPUtils(String USERNAME, String HOST, int PORT) {
        username = USERNAME;
        host = HOST;
        port = PORT;
        privateKey = BOCCCConstant.loginPrivateKeyPath;
    }

    public SFTPUtils(){}


    /**
     * 连接sftp服务器
     */
    public void login(){
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
    public void logout(){
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
     * @param basePath  服务器的基础路径
     * @param directory  上传到该目录
     * @param sftpFileName  sftp端文件名
     * @param input   输入流
     */
    public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String [] dirs=directory.split("/");
            String tempPath=basePath;
            for(String dir:dirs){
                if(null== dir || "".equals(dir)){
                    continue;
                }
                tempPath+="/"+dir;
                try{
                    sftp.cd(tempPath);
                }catch(SftpException ex){
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        //上传文件
        sftp.put(input, sftpFileName);
    }

    /**
     * 下载文件。
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        byte[] fileData = IOUtils.toByteArray(is);

        return fileData;
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
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
    public static void setPassword(String PASSWORD) {
        password = PASSWORD;
    }

    @Value("${boccc.sftp.host}")
    public static void setHost(String HOST) {
        host = HOST;
    }

    @Value("${boccc.sftp.port}")
    public static void setPort(int PORT) {
        port = PORT;
    }

    public static void upload(){
        logger.info("上传文件中");
    }

    public static void download(){
        logger.info("下载文件中");
    }
}
