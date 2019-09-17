/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.UploadCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.UploadException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件上传公共类
 *
 * @author niexiao
 * @created 2018/10/21
 */
@Component
public class UploadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);

    private static String COS_SECRET_ID;
    private static String COS_SECRET_KEY;
    private static String COS_BUCKET_NAME;
    private static String COS_REGION_NAME;
    private static String COS_ACCESS_PATH_IMAGE;
    private static String COS_ACCESS_PATH_EXCEL;

    /**
     * 图片类型
     */
    public static final int FILE_TYPE_IMAGE = 1;
    /**
     * excel类型
     */
    public static final int FILE_TYPE_EXCEL = 2;


    private static volatile TransferManager _transferManager = null;

    private static TransferManager getTransferManager() {
        if (_transferManager == null) {
            synchronized (UploadUtil.class) {
                if (_transferManager == null) {
                    ExecutorService threadPool = Executors.newFixedThreadPool(4);
                    // 1 初始化用户身份信息（secretId, secretKey）。
                    COSCredentials cred = new BasicCOSCredentials(COS_SECRET_ID, COS_SECRET_KEY);
                    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
                    // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
                    ClientConfig clientConfig = new ClientConfig(new Region(COS_REGION_NAME));
                    // 3 生成 cos 客户端。
                    COSClient cosClient = new COSClient(cred, clientConfig);
                    // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
                    _transferManager = new TransferManager(cosClient, threadPool);
                }
            }
        }
        return _transferManager;
    }

    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @param path          文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */

    public static String uploadFile2Server(MultipartFile multipartFile, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = createFileName(multipartFile);
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();
        } catch (IOException e) {
            //抛出自定义异常
            throw new UploadException(UploadCodeEnum.OTHER_ERROR);
        }
        return fileName;
    }

    /**
     * 将文件上传到COS上
     *
     * @param multipartFile
     * @param fileType
     * @return
     */
    public static String uploadFile2COS(MultipartFile multipartFile, int fileType) {
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = COS_BUCKET_NAME;
        //获取图片名称
        String fileName = createFileName(multipartFile);
        try {
            //从输入流上传(需提前告知输入流的长度, 否则可能导致 oom)
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置输入流长度
            objectMetadata.setContentLength(fileInputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, getAccessPath(fileType) + fileName, fileInputStream, objectMetadata);
            Upload upload = getTransferManager().upload(putObjectRequest);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            UploadResult uploadResult = upload.waitForUploadResult();
        } catch (CosServiceException e) {
            LOGGER.error("uploadFile2COS发生COS服务端异常,message:{}", e.getMessage());
            throw new UploadException(UploadCodeEnum.COS_SERVICE_ERROR, e.getMessage());
        } catch (CosClientException e) {
            LOGGER.error("uploadFile2COS发生COS客户端异常,message:{}", e.getMessage());
            throw new UploadException(UploadCodeEnum.COS_CLIENT_ERROR, e.getMessage());
        } catch (IOException | InterruptedException e) {
            //抛出自定义异常
            LOGGER.error("uploadFile2COS发生未知异常,message:{}", e.getMessage());
            throw new UploadException(UploadCodeEnum.OTHER_ERROR);
        }
        return fileName;
    }


    private static String createFileName(MultipartFile multipartFile) {
        String suffix = getSuffix(multipartFile.getOriginalFilename());
        return DateUtil.yyyyMMDDhhmmssSSS() + suffix;
    }

    private static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private static String getAccessPath(int fileType) {
        switch (fileType) {
            default:
            case FILE_TYPE_IMAGE:
                return COS_ACCESS_PATH_IMAGE;
            case FILE_TYPE_EXCEL:
                return COS_ACCESS_PATH_EXCEL;
        }
    }


    @Value("${cos.secret-id}")
    public void setCosSecretId(String cosSecretId) {
        COS_SECRET_ID = cosSecretId;
    }

    @Value("${cos.secret-key}")
    public void setCosSecretKey(String cosSecretKey) {
        COS_SECRET_KEY = cosSecretKey;
    }

    @Value("${cos.bucket-name}")
    public void setCosBucketName(String cosBucketName) {
        COS_BUCKET_NAME = cosBucketName;
    }

    @Value("${cos.region-name}")
    public void setCosRegionName(String cosRegionName) {
        COS_REGION_NAME = cosRegionName;
    }

    @Value("${cos.access-path.image}")
    public void setCosAccessPathImage(String cosAccessPathImage) {
        COS_ACCESS_PATH_IMAGE = cosAccessPathImage;
    }

    @Value("${cos.access-path.excel}")
    public void setCosAccessPathExcel(String cosAccessPathExcel) {
        COS_ACCESS_PATH_EXCEL = cosAccessPathExcel;
    }

}