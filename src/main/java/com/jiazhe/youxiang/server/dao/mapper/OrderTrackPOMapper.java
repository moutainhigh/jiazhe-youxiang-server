package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.OrderTrackPO;
import com.jiazhe.youxiang.server.domain.po.OrderTrackPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderTrackPOMapper {
    int countByExample(OrderTrackPOExample example);

    int deleteByExample(OrderTrackPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderTrackPO record);

    int insertSelective(OrderTrackPO record);

    List<OrderTrackPO> selectByExampleWithBLOBs(OrderTrackPOExample example);

    List<OrderTrackPO> selectByExample(OrderTrackPOExample example);

    OrderTrackPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderTrackPO record, @Param("example") OrderTrackPOExample example);

    int updateByExampleWithBLOBs(@Param("record") OrderTrackPO record, @Param("example") OrderTrackPOExample example);

    int updateByExample(@Param("record") OrderTrackPO record, @Param("example") OrderTrackPOExample example);

    int updateByPrimaryKeySelective(OrderTrackPO record);

    int updateByPrimaryKeyWithBLOBs(OrderTrackPO record);

    int updateByPrimaryKey(OrderTrackPO record);
}