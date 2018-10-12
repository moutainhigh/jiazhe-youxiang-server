package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.CustomerPO;
import com.jiazhe.youxiang.server.domain.po.CustomerPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerPOMapper {
    int countByExample(CustomerPOExample example);

    int deleteByExample(CustomerPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerPO record);

    int insertSelective(CustomerPO record);

    List<CustomerPO> selectByExample(CustomerPOExample example);

    CustomerPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerPO record, @Param("example") CustomerPOExample example);

    int updateByExample(@Param("record") CustomerPO record, @Param("example") CustomerPOExample example);

    int updateByPrimaryKeySelective(CustomerPO record);

    int updateByPrimaryKey(CustomerPO record);
}