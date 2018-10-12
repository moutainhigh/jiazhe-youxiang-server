package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoPOMapper {
    int countByExample(OrderInfoPOExample example);

    int deleteByExample(OrderInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfoPO record);

    int insertSelective(OrderInfoPO record);

    List<OrderInfoPO> selectByExample(OrderInfoPOExample example);

    OrderInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderInfoPO record, @Param("example") OrderInfoPOExample example);

    int updateByExample(@Param("record") OrderInfoPO record, @Param("example") OrderInfoPOExample example);

    int updateByPrimaryKeySelective(OrderInfoPO record);

    int updateByPrimaryKey(OrderInfoPO record);
}