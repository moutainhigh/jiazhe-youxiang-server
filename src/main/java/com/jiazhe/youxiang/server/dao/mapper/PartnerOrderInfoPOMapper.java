package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PartnerOrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderInfoPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnerOrderInfoPOMapper {
    int countByExample(PartnerOrderInfoPOExample example);

    int deleteByExample(PartnerOrderInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartnerOrderInfoPO record);

    int insertSelective(PartnerOrderInfoPO record);

    List<PartnerOrderInfoPO> selectByExample(PartnerOrderInfoPOExample example);

    PartnerOrderInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartnerOrderInfoPO record, @Param("example") PartnerOrderInfoPOExample example);

    int updateByExample(@Param("record") PartnerOrderInfoPO record, @Param("example") PartnerOrderInfoPOExample example);

    int updateByPrimaryKeySelective(PartnerOrderInfoPO record);

    int updateByPrimaryKey(PartnerOrderInfoPO record);
}