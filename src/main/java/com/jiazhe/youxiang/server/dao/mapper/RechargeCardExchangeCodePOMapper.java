package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeCardExchangeCodePOMapper {
    int countByExample(RechargeCardExchangeCodePOExample example);

    int deleteByExample(RechargeCardExchangeCodePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeCardExchangeCodePO record);

    int insertSelective(RechargeCardExchangeCodePO record);

    List<RechargeCardExchangeCodePO> selectByExample(RechargeCardExchangeCodePOExample example);

    RechargeCardExchangeCodePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeCardExchangeCodePO record, @Param("example") RechargeCardExchangeCodePOExample example);

    int updateByExample(@Param("record") RechargeCardExchangeCodePO record, @Param("example") RechargeCardExchangeCodePOExample example);

    int updateByPrimaryKeySelective(RechargeCardExchangeCodePO record);

    int updateByPrimaryKey(RechargeCardExchangeCodePO record);
}