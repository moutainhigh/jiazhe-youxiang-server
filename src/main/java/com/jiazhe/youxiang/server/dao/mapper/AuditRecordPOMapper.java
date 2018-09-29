package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import com.jiazhe.youxiang.server.domain.po.AuditRecordPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuditRecordPOMapper {
    int countByExample(AuditRecordPOExample example);

    int deleteByExample(AuditRecordPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuditRecordPO record);

    int insertSelective(AuditRecordPO record);

    List<AuditRecordPO> selectByExample(AuditRecordPOExample example);

    AuditRecordPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuditRecordPO record, @Param("example") AuditRecordPOExample example);

    int updateByExample(@Param("record") AuditRecordPO record, @Param("example") AuditRecordPOExample example);

    int updateByPrimaryKeySelective(AuditRecordPO record);

    int updateByPrimaryKey(AuditRecordPO record);
}