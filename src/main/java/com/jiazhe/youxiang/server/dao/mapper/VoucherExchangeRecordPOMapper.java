package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherExchangeRecordPOMapper {
    int countByExample(VoucherExchangeRecordPOExample example);

    int deleteByExample(VoucherExchangeRecordPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoucherExchangeRecordPO record);

    int insertSelective(VoucherExchangeRecordPO record);

    List<VoucherExchangeRecordPO> selectByExample(VoucherExchangeRecordPOExample example);

    VoucherExchangeRecordPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoucherExchangeRecordPO record, @Param("example") VoucherExchangeRecordPOExample example);

    int updateByExample(@Param("record") VoucherExchangeRecordPO record, @Param("example") VoucherExchangeRecordPOExample example);

    int updateByPrimaryKeySelective(VoucherExchangeRecordPO record);

    int updateByPrimaryKey(VoucherExchangeRecordPO record);
}