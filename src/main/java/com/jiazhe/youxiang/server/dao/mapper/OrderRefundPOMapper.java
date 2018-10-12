package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.OrderRefundPO;
import com.jiazhe.youxiang.server.domain.po.OrderRefundPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderRefundPOMapper {
    int countByExample(OrderRefundPOExample example);

    int deleteByExample(OrderRefundPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRefundPO record);

    int insertSelective(OrderRefundPO record);

    List<OrderRefundPO> selectByExample(OrderRefundPOExample example);

    OrderRefundPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderRefundPO record, @Param("example") OrderRefundPOExample example);

    int updateByExample(@Param("record") OrderRefundPO record, @Param("example") OrderRefundPOExample example);

    int updateByPrimaryKeySelective(OrderRefundPO record);

    int updateByPrimaryKey(OrderRefundPO record);
}