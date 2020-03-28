package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargeOffPointPOMapper {
    int countByExample(ChargeOffPointPOExample example);

    int deleteByExample(ChargeOffPointPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeOffPointPO record);

    int insertSelective(ChargeOffPointPO record);

    List<ChargeOffPointPO> selectByExample(ChargeOffPointPOExample example);

    ChargeOffPointPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeOffPointPO record, @Param("example") ChargeOffPointPOExample example);

    int updateByExample(@Param("record") ChargeOffPointPO record, @Param("example") ChargeOffPointPOExample example);

    int updateByPrimaryKeySelective(ChargeOffPointPO record);

    int updateByPrimaryKey(ChargeOffPointPO record);
}