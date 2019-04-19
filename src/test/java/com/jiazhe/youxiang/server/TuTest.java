package com.jiazhe.youxiang.server;

import com.jiazhe.youxiang.base.util.RedisUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tu用于测试的
 * Created by TU on 2018/10/12.
 */
public class TuTest extends TemplateApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(TuTest.class);


    @Test
    public void testSalt() {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123";//密码原值
        ByteSource salt = ByteSource.Util.bytes("2256");//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        logger.info(result.toString());
    }

    @Test
    public void testRedisSet(){
        RedisUtils.set("name","tujian",5);
    }
}
