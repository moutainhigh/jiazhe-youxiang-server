package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.CustomerAddressPO;
import com.jiazhe.youxiang.server.domain.po.CustomerAddressPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerAddressPOMapper {
    int countByExample(CustomerAddressPOExample example);

    int deleteByExample(CustomerAddressPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerAddressPO record);

    int insertSelective(CustomerAddressPO record);

    List<CustomerAddressPO> selectByExample(CustomerAddressPOExample example);

    CustomerAddressPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerAddressPO record, @Param("example") CustomerAddressPOExample example);

    int updateByExample(@Param("record") CustomerAddressPO record, @Param("example") CustomerAddressPOExample example);

    int updateByPrimaryKeySelective(CustomerAddressPO record);

    int updateByPrimaryKey(CustomerAddressPO record);
}