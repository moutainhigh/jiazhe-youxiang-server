/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.domain.po.SysLogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
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
     * @param parentCode
     * @param status
     * @return
     */
    int updateStatusByCityCode(@Param("parentCode") String parentCode, @Param("status") Byte status);

    List<SysCityPO> query(@Param("parentCode") String parentCode, @Param("level") Integer level,@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer count(@Param("parentCode") String parentCode,@Param("level") Integer level);


}
