package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ProjectPO;
import com.jiazhe.youxiang.server.domain.po.SysLogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPOManualMapper {

    List<ProjectPO> query(@Param("name") String name, @Param("status") Integer status, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer count(@Param("name") String name, @Param("status") Integer status);
}