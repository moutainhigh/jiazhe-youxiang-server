package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeCardPOMapper {
    int countByExample(RechargeCardPOExample example);

    int deleteByExample(RechargeCardPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeCardPO record);

    int insertSelective(RechargeCardPO record);

    List<RechargeCardPO> selectByExample(RechargeCardPOExample example);

    RechargeCardPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeCardPO record, @Param("example") RechargeCardPOExample example);

    int updateByExample(@Param("record") RechargeCardPO record, @Param("example") RechargeCardPOExample example);

    int updateByPrimaryKeySelective(RechargeCardPO record);

    int updateByPrimaryKey(RechargeCardPO record);
}