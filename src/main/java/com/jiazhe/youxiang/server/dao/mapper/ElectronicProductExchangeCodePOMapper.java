package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElectronicProductExchangeCodePOMapper {
    int countByExample(ElectronicProductExchangeCodePOExample example);

    int deleteByExample(ElectronicProductExchangeCodePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ElectronicProductExchangeCodePO record);

    int insertSelective(ElectronicProductExchangeCodePO record);

    List<ElectronicProductExchangeCodePO> selectByExample(ElectronicProductExchangeCodePOExample example);

    ElectronicProductExchangeCodePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ElectronicProductExchangeCodePO record, @Param("example") ElectronicProductExchangeCodePOExample example);

    int updateByExample(@Param("record") ElectronicProductExchangeCodePO record, @Param("example") ElectronicProductExchangeCodePOExample example);

    int updateByPrimaryKeySelective(ElectronicProductExchangeCodePO record);

    int updateByPrimaryKey(ElectronicProductExchangeCodePO record);
}