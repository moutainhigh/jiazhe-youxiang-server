package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ChargeOffPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargeOffPOMapper {
    int countByExample(ChargeOffPOExample example);

    int deleteByExample(ChargeOffPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeOffPO record);

    int insertSelective(ChargeOffPO record);

    List<ChargeOffPO> selectByExample(ChargeOffPOExample example);

    ChargeOffPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeOffPO record, @Param("example") ChargeOffPOExample example);

    int updateByExample(@Param("record") ChargeOffPO record, @Param("example") ChargeOffPOExample example);

    int updateByPrimaryKeySelective(ChargeOffPO record);

    int updateByPrimaryKey(ChargeOffPO record);
}