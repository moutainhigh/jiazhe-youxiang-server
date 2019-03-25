package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargeReceiptPOMapper {
    int countByExample(ChargeReceiptPOExample example);

    int deleteByExample(ChargeReceiptPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeReceiptPO record);

    int insertSelective(ChargeReceiptPO record);

    List<ChargeReceiptPO> selectByExample(ChargeReceiptPOExample example);

    ChargeReceiptPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeReceiptPO record, @Param("example") ChargeReceiptPOExample example);

    int updateByExample(@Param("record") ChargeReceiptPO record, @Param("example") ChargeReceiptPOExample example);

    int updateByPrimaryKeySelective(ChargeReceiptPO record);

    int updateByPrimaryKey(ChargeReceiptPO record);
}