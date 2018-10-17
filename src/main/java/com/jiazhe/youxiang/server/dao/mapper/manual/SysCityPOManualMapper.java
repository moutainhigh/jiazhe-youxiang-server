/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dao.mapper.manual;

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
     * @param parentCode
     * @param status
     * @return
     */
    int updateStatusByParentCode(@Param("parentCode") String parentCode, @Param("status") Byte status);

}
