package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysLogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogPOManualMapper {

    List<SysLogPO> query(@Param("moduleName") String moduleName,
                         @Param("operate") String operate,
                         @Param("level") Integer level,
                         @Param("operatorName") String operatorName,
                         @Param("ip") String ip,
                         @Param("startTime") Long startTime,
                         @Param("endTime") Long endTime,
                         @Param("offset") Integer offset,
                         @Param("limit") Integer limit);

    Integer count(@Param("moduleName") String moduleName,
                  @Param("operate") String operate,
                  @Param("level") Integer level,
                  @Param("operatorName") String operatorName,
                  @Param("ip") String ip,
                  @Param("startTime") Long startTime,
                  @Param("endTime") Long endTime);

}