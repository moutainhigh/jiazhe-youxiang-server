package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeCardExchangeCodeBatchPOMapper {
    int countByExample(RechargeCardExchangeCodeBatchPOExample example);

    int deleteByExample(RechargeCardExchangeCodeBatchPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeCardExchangeCodeBatchPO record);

    int insertSelective(RechargeCardExchangeCodeBatchPO record);

    List<RechargeCardExchangeCodeBatchPO> selectByExample(RechargeCardExchangeCodeBatchPOExample example);

    RechargeCardExchangeCodeBatchPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeCardExchangeCodeBatchPO record, @Param("example") RechargeCardExchangeCodeBatchPOExample example);

    int updateByExample(@Param("record") RechargeCardExchangeCodeBatchPO record, @Param("example") RechargeCardExchangeCodeBatchPOExample example);

    int updateByPrimaryKeySelective(RechargeCardExchangeCodeBatchPO record);

    int updateByPrimaryKey(RechargeCardExchangeCodeBatchPO record);
}