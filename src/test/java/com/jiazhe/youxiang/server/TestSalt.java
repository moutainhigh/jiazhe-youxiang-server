package com.jiazhe.youxiang.server;

import com.jiazhe.youxiang.server.biz.rechargecard.RCBiz;
import com.jiazhe.youxiang.server.biz.voucher.VoucherBiz;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by TU on 2018/10/12.
 */
public class TestSalt {

    private static final Logger logger = LoggerFactory.getLogger(TestSalt.class);

        public static void main(String[] args) {
            String hashAlgorithmName = "MD5";//加密方式
            Object crdentials = "123";//密码原值
            ByteSource salt = ByteSource.Util.bytes("2256");//以账号作为盐值
            int hashIterations = 1024;//加密1024次
            Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
            logger.info(result.toString());

        }
}
