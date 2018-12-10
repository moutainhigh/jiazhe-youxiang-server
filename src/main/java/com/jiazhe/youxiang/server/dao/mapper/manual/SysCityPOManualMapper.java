/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
@CacheConfig(cacheNames = "sys_city_cache")
public interface SysCityPOManualMapper {

    /**
     * 批量开通或关闭城市
     *
     * @param cityCode
     * @param status
     * @return
     */
    int updateStatusByCityCodes(@Param("cityCodes") List<String> cityCode, @Param("status") Byte status);

    /**
     * 开通或关闭整个省份
     *
     * @param cityCode
     * @param status
     * @return
     */
    int updateStatusByCityCode(@Param("cityCode") String cityCode, @Param("status") Byte status, @Param("isCascade") Boolean isCascade);

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    List<SysCityPO> query(@Param("parentCode") String parentCode, @Param("level") Integer level, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    Integer count(@Param("parentCode") String parentCode, @Param("level") Integer level);


}
