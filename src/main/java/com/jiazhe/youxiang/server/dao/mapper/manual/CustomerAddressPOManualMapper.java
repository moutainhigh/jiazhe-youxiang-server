package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.CustomerAddressPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerAddressPOManualMapper {

    Integer insertSelectiveGetID(CustomerAddressPO customerAddressPO);

    Integer count(@Param("customerId") Integer customerId);

    List<CustomerAddressPO> query(@Param("customerId") Integer customerId,@Param("offset") Integer offset, @Param("limit")Integer limit);
}