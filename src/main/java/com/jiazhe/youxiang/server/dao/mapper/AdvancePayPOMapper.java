package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.AdvancePayPO;
import com.jiazhe.youxiang.server.domain.po.AdvancePayPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvancePayPOMapper {
    int countByExample(AdvancePayPOExample example);

    int deleteByExample(AdvancePayPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdvancePayPO record);

    int insertSelective(AdvancePayPO record);

    List<AdvancePayPO> selectByExample(AdvancePayPOExample example);

    AdvancePayPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdvancePayPO record, @Param("example") AdvancePayPOExample example);

    int updateByExample(@Param("record") AdvancePayPO record, @Param("example") AdvancePayPOExample example);

    int updateByPrimaryKeySelective(AdvancePayPO record);

    int updateByPrimaryKey(AdvancePayPO record);
}