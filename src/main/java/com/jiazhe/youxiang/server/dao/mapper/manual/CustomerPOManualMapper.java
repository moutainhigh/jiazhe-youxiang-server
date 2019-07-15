/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.CustomerPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public interface CustomerPOManualMapper {

    public Integer count(@Param("mobile") String mobile, @Param("name") String name);

    public List<CustomerPO> query(@Param("mobile") String mobile, @Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit);
}