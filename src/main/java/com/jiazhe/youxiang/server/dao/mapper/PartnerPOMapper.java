package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PartnerPO;
import com.jiazhe.youxiang.server.domain.po.PartnerPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnerPOMapper {
    int countByExample(PartnerPOExample example);

    int deleteByExample(PartnerPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartnerPO record);

    int insertSelective(PartnerPO record);

    List<PartnerPO> selectByExample(PartnerPOExample example);

    PartnerPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartnerPO record, @Param("example") PartnerPOExample example);

    int updateByExample(@Param("record") PartnerPO record, @Param("example") PartnerPOExample example);

    int updateByPrimaryKeySelective(PartnerPO record);

    int updateByPrimaryKey(PartnerPO record);
}