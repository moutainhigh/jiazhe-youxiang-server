package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author TU
 * @description 用于redis缓存的操作
 * @date 2019-04-04.
 */
@Component
public class RedisUtils {

    @Value("${spring.profiles.active}")
    private String ENVIRONMENT;

    @Autowired
    private RedisTemplate redisTemplate;

    public static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
        redisUtils.redisTemplate = this.redisTemplate;
        redisUtils.ENVIRONMENT = this.ENVIRONMENT;
    }

    /**
     * 设置过期时间，固定以毫秒为单位
     * @param key
     * @param value
     * @param millisecond
     */
    public static void set(String key, Object value, long millisecond) {
        redisUtils.redisTemplate.opsForValue().set(redisUtils.ENVIRONMENT + CommonConstant.REDIS_KEY_CONNECT + key, value, millisecond, TimeUnit.MILLISECONDS);
    }

    /**
     * 不设置过期时间
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        redisUtils.redisTemplate.opsForValue().set(redisUtils.ENVIRONMENT + CommonConstant.REDIS_KEY_CONNECT + key, value);
    }

    public static Object get(String key) {
        if (redisUtils.redisTemplate.hasKey(redisUtils.ENVIRONMENT + CommonConstant.REDIS_KEY_CONNECT + key)) {
            return redisUtils.redisTemplate.opsForValue().get(redisUtils.ENVIRONMENT + CommonConstant.REDIS_KEY_CONNECT + key);
        } else {
            return null;
        }
    }
}
