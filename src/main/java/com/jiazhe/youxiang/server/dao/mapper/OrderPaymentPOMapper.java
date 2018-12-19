package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderPaymentPOMapper {
    int countByExample(OrderPaymentPOExample example);

    int deleteByExample(OrderPaymentPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPaymentPO record);

    int insertSelective(OrderPaymentPO record);

    List<OrderPaymentPO> selectByExample(OrderPaymentPOExample example);

    OrderPaymentPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderPaymentPO record, @Param("example") OrderPaymentPOExample example);

    int updateByExample(@Param("record") OrderPaymentPO record, @Param("example") OrderPaymentPOExample example);

    int updateByPrimaryKeySelective(OrderPaymentPO record);

    int updateByPrimaryKey(OrderPaymentPO record);
}