package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeCardExchangeRecordPOMapper {
    int countByExample(RechargeCardExchangeRecordPOExample example);

    int deleteByExample(RechargeCardExchangeRecordPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeCardExchangeRecordPO record);

    int insertSelective(RechargeCardExchangeRecordPO record);

    List<RechargeCardExchangeRecordPO> selectByExample(RechargeCardExchangeRecordPOExample example);

    RechargeCardExchangeRecordPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeCardExchangeRecordPO record, @Param("example") RechargeCardExchangeRecordPOExample example);

    int updateByExample(@Param("record") RechargeCardExchangeRecordPO record, @Param("example") RechargeCardExchangeRecordPOExample example);

    int updateByPrimaryKeySelective(RechargeCardExchangeRecordPO record);

    int updateByPrimaryKey(RechargeCardExchangeRecordPO record);
}