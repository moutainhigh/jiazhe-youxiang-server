/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.bocdc;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019-12-04
 */
public class SFTPUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SFTPUtils.class);

    static private Session session = null;
    static private Channel channel = null;
    static private int timeout = 60000; //超时数,一分钟

    /**
     * 传入一个通道对象
     *
     * @param username 远程要连接的服务器的用户名
     * @param password 远程要连接的服务器的密码
     * @param ip       远程服务器ip
     * @param port     远程服务器的ssh服务端口
     * @return ChannelSftp返回指向这个通道指定的地址的channel实例
     * @throws JSchException
     */
    public static ChannelSftp getChannel(String username, String password, String privateKey, String ip, int port) throws JSchException {
        JSch jsch = new JSch(); // 创建JSch对象
        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(username, ip, port);
        LOGGER.info("Session created...");
        if (privateKey != null) {
            // 设置私钥
            jsch.addIdentity(privateKey);
        }
        if (password != null) {
            session.setPassword(password); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        LOGGER.info("Session connected, Opening Channel...");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        LOGGER.info("Connected successfully to ip :{}, ftpUsername is :{}, return :{}",
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
}
