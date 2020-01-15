package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PartnerOrderTrackPO;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderTrackPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnerOrderTrackPOMapper {
    int countByExample(PartnerOrderTrackPOExample example);

    int deleteByExample(PartnerOrderTrackPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartnerOrderTrackPO record);

    int insertSelective(PartnerOrderTrackPO record);

    List<PartnerOrderTrackPO> selectByExampleWithBLOBs(PartnerOrderTrackPOExample example);

    List<PartnerOrderTrackPO> selectByExample(PartnerOrderTrackPOExample example);

    PartnerOrderTrackPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartnerOrderTrackPO record, @Param("example") PartnerOrderTrackPOExample example);

    int updateByExampleWithBLOBs(@Param("record") PartnerOrderTrackPO record, @Param("example") PartnerOrderTrackPOExample example);

    int updateByExample(@Param("record") PartnerOrderTrackPO record, @Param("example") PartnerOrderTrackPOExample example);

    int updateByPrimaryKeySelective(PartnerOrderTrackPO record);

    int updateByPrimaryKeyWithBLOBs(PartnerOrderTrackPO record);

    int updateByPrimaryKey(PartnerOrderTrackPO record);
}