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
import com.jiazhe.youxiang.base.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/**
 * @author tu
 * @version 1.0
 * @description SFTP工具类，定时自动下载、上传
 * @created 2019-09-03 20:53
 */
public class AutoSFTPUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoSFTPUtils.class);

    static private Session session = null;

    static private Channel channel = null;

    static private int timeout = 60000;

    /**
     * 获取一个通道对象
     *
     * @param username 远程要连接的服务器的用户名
     * @param password 远程要连接的服务器的密码
     * @param ip       远程服务器ip
     * @param port     远程服务器的ssh服务端口
     * @return ChannelSftp返回指向这个通道指定的地址的channel实例
     * @throws JSchException
     */
    public static ChannelSftp getChannel(String username, String password, String privateKey, String ip, int port) throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, ip, port);
        logger.info("Session created...");
        if (privateKey != null) {
            jsch.addIdentity(privateKey);
        }
        if (password != null) {
            session.setPassword(password);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(timeout);
        session.connect();
        logger.info("Session connected, Opening Channel...");
        channel = session.openChannel("sftp");
        channel.connect();
        logger.info("Connected successfully to ip :{}, ftpUsername is :{}, return :{}",
                ip, username, channel);
        return (ChannelSftp) channel;
    }

    /**
     * 关闭channel和session
     *
     * @throws Exception
     */
    public static void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * 将当天的文件全部上传至中行信用卡服务器
     *
     * @throws FileNotFoundException
     * @throws SftpException
     * @throws JSchException
     */
    public static void upload() throws FileNotFoundException, SftpException, JSchException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = getChannel(BOCCCConstant.username, null, BOCCCConstant.loginPrivateKeyPath, BOCCCConstant.host, BOCCCConstant.port);
            // 二、 判断远程路径dstDirPath是否存在(通道配置的路径)
            try {
                Vector dir = channelSftp.ls(BOCCCConstant.outPath);
                if (dir == null) {
                    channelSftp.mkdir(BOCCCConstant.outPath);
                }
            } catch (SftpException e) {
                logger.error("BOCCC-UPLOAD:{}", e.getMessage());
            }
            // 三、 推送文件
            try {
                File uploadPath = new File(BOCCCConstant.uploadPath + BOCCCUtils.getToday());
                if (uploadPath.exists()) {
                    File[] fs = uploadPath.listFiles();
                    for (File file : fs) {
                        channelSftp.put(new FileInputStream(file), BOCCCConstant.outPath + "/" + file.getName());
                    }
                }
            } catch (SftpException e) {
                logger.error("An error occurred during sftp push, send data fail, the target path is :{}", BOCCCConstant.outPath);
            }
        } finally {
            if (channelSftp != null) {
                channelSftp.quit();
            }
            try {
                closeChannel();
            } catch (Exception e) {
                logger.info("BOCCC-UPLOAD Exception:{}", e.getMessage());
            }
        }
        logger.info("BOCCC-UPLOAD:上传文件完成");
    }


    /**
     * 下载文件，文件名不改变。
     *
     * @param sftp
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param savePath     存在本地的路径
     */
    public static void download(ChannelSftp sftp, String directory, String downloadFile, String savePath) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(savePath + "\\" + downloadFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    public static boolean isExistDir(ChannelSftp sftp, String path) {
        logger.info("上传文件中 执行[isExistDir]");
        boolean isExist = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(path);
            logger.info("上传文件中 SftpATTRS sftpATTRS = sftp.lstat(path) sftpATTRS:{}", JacksonUtil.toJSon(sftpATTRS));
            isExist = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            logger.info("上传文件中 Exception:{}", e.getMessage());
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isExist = false;
            }
        }
        return isExist;
    }

    /**
     * 列出远程目录下的文件
     *
     * @param directory 要列出的目录
     */
    public static Vector<?> listFiles(ChannelSftp sftp, String directory) throws SftpException {
        return sftp.ls(directory);
    }

    public static void download() throws SftpException, FileNotFoundException {
        logger.info("BOCCC-DOWNLOAD：下载文件开始...");
        ChannelSftp sftp = null;
        try {
            sftp = getChannel(BOCCCConstant.username, null, BOCCCConstant.loginPrivateKeyPath, BOCCCConstant.host, BOCCCConstant.port);
            String downloadPath = BOCCCConstant.downloadPath + BOCCCUtils.getToday();
            //判断下载到本地的路径是否存在
            File localDirectory = new File(downloadPath);
            if (!localDirectory.exists()) {
                localDirectory.mkdirs();
            }
            if (isExistDir(sftp, BOCCCConstant.inPath)) {
                Vector v = listFiles(sftp, BOCCCConstant.inPath);
                Iterator it = v.iterator();
                while (it.hasNext()) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                    String fileName = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!attrs.isDir() && fileName.contains(BOCCCUtils.getYesterday())) {
                        download(sftp, BOCCCConstant.inPath, entry.getFilename(), downloadPath);
                    }
                }
            } else {
                logger.error("BOCCC-DOWNLOAD：中行信用卡服务器待下载文件存放路径定义有误！！！");
            }
        } catch (Exception e) {
            logger.error("BOCCC-DOWNLOAD Exception：{}", e.getMessage());
        } finally {
            if (sftp != null) {
                sftp.quit();
            }
            try {
                closeChannel();
            } catch (Exception e) {
                logger.info("BOCCC-DOWNLOAD Exception：{}", e.getMessage());
            }
        }
        logger.info("BOCCC-DOWNLOAD：下载文件完成！！！");
    }

}
