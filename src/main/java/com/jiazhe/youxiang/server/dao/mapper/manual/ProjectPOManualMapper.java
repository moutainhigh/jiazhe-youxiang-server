package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ProjectPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "project_cache")
public interface ProjectPOManualMapper {

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    List<ProjectPO> query(@Param("name") String name, @Param("status") Integer status, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    Integer count(@Param("name") String name, @Param("status") Integer status);
}