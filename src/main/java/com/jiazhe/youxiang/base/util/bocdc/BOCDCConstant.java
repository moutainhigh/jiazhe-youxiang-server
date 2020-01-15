package com.jiazhe.youxiang.base.util.bocdc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author niexiao
 * @description 中行储蓄卡常量  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@Component
public class BOCDCConstant {

    /**
     * 执行环境
     */
    public static String[] BOCDC_ENVIRONMENT = {"test", "online"};

    /**
     * 成功代码
     */
    public static final String CODE_SUCCESS = "0000";
    /**
     * 商户ID
     */
    public static String MER_ID;

    /**
     * 中行信用卡实时接口：已使用请求
     */
    public static String REAL_TIME_USED_URL;

    /**
     * 中行信用卡实时接口：已退货请求
     */
    public static String REAL_TIME_REFUND_URL;

    /**
     * 状态检查HTTP接口
     */
    public static String STATUS_CHECK_HTTP_URL;
    public static String ROOT_PATH;
    public static String uploadPath;
    public static String loginPrivateKeyPath;
    public static String reconciliationPath;
    public static String publicKeyPath;
    public static String privateKeyPath;
    public static String merchantCode;
    public static String sourceFileName;
    public static String zipFileName;
    public static String pgpFileName;

    /**
     * SFTP 登录用户名
     */
    public static String username;
    /**
     * 私钥
     */
    public static String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    public static String host;
    /**
     * SFTP 端口
     */
    public static int port;

    /**
     * 上传到中行的根路径
     */
    public static String outPath;

    @Value("${bocdc.status_check.http_url}")
    public void setHttpUrl(String httpUrl) {
        STATUS_CHECK_HTTP_URL = httpUrl;
    }

    @Value("${bocdc.merid}")
    public void setMerId(String merId) {
        MER_ID = merId;
    }

    @Value("${boccc.realtime.used_url}")
    public void setUsedUrl(String usedUrl) {
        REAL_TIME_USED_URL = usedUrl;
    }

    @Value("${boccc.realtime.refund_url}")
    public void setRefundUrl(String refundUrl) {
        REAL_TIME_REFUND_URL = refundUrl;
    }

    @Value("${bocdc.sftp.rootPath}")
    public void setRootpath(String rootPath) {
        BOCDCConstant.ROOT_PATH = rootPath;
    }

    @Value("${bocdc.sftp.uploadPath}")
    public void setUploadPath(String uploadPath) {
        BOCDCConstant.uploadPath = uploadPath;
    }

    @Value("${bocdc.sftp.loginPrivateKeyPath}")
    public void setLoginPrivateKeyPath(String loginPrivateKeyPath) {
        BOCDCConstant.loginPrivateKeyPath = loginPrivateKeyPath;
    }

    @Value("${bocdc.sftp.reconciliationPath}")
    public void setReconciliationPath(String reconciliationPath) {
        BOCDCConstant.reconciliationPath = reconciliationPath;
    }

    @Value("${bocdc.sftp.publicKeyPath}")
    public void setPublicKeyPath(String publicKeyPath) {
        BOCDCConstant.publicKeyPath = publicKeyPath;
    }

    @Value("${bocdc.sftp.privateKeyPath}")
    public void setPrivateKeyPath(String privateKeyPath) {
        BOCDCConstant.privateKeyPath = privateKeyPath;
    }

    @Value("${bocdc.sftp.merchantCode}")
    public void setMerchantCode(String merchantCode) {
        BOCDCConstant.merchantCode = merchantCode;
    }

    @Value("${bocdc.sftp.sourceFileName}")
    public void setSourceFileName(String sourceFileName) {
        BOCDCConstant.sourceFileName = sourceFileName;
    }

    @Value("${bocdc.sftp.zipFileName}")
    public void setZipFileName(String zipFileName) {
        BOCDCConstant.zipFileName = zipFileName;
    }

    @Value("${bocdc.sftp.pgpFileName}")
    public void setPgpFileName(String pgpFileName) {
        BOCDCConstant.pgpFileName = pgpFileName;
    }

//    @Value("${bocdc.sftp.privateKey}")
//    public void setPrivateKey(String privateKey) {
//        BOCDCConstant.privateKey = privateKey;
//    }

    @Value("${bocdc.sftp.username}")
    public void setUsername(String USERNAME) {
        username = USERNAME;
    }

    @Value("${bocdc.sftp.host}")
    public void setHost(String HOST) {
        host = HOST;
    }

    @Value("${bocdc.sftp.port}")
    public void setPort(int PORT) {
        port = PORT;
    }

    @Value("${bocdc.sftp.out}")
    public void setOutPath(String OUTPATH) {
        outPath = OUTPATH;
    }


}
