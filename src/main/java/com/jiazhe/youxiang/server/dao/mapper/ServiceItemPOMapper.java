package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ServiceItemPO;
import com.jiazhe.youxiang.server.domain.po.ServiceItemPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceItemPOMapper {
    int countByExample(ServiceItemPOExample example);

    int deleteByExample(ServiceItemPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceItemPO record);

    int insertSelective(ServiceItemPO record);

    List<ServiceItemPO> selectByExample(ServiceItemPOExample example);

    ServiceItemPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceItemPO record, @Param("example") ServiceItemPOExample example);

    int updateByExample(@Param("record") ServiceItemPO record, @Param("example") ServiceItemPOExample example);

    int updateByPrimaryKeySelective(ServiceItemPO record);

    int updateByPrimaryKey(ServiceItemPO record);
}