package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherExchangeCodeBatchPOMapper {
    int countByExample(VoucherExchangeCodeBatchPOExample example);

    int deleteByExample(VoucherExchangeCodeBatchPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoucherExchangeCodeBatchPO record);

    int insertSelective(VoucherExchangeCodeBatchPO record);

    List<VoucherExchangeCodeBatchPO> selectByExample(VoucherExchangeCodeBatchPOExample example);

    VoucherExchangeCodeBatchPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoucherExchangeCodeBatchPO record, @Param("example") VoucherExchangeCodeBatchPOExample example);

    int updateByExample(@Param("record") VoucherExchangeCodeBatchPO record, @Param("example") VoucherExchangeCodeBatchPOExample example);

    int updateByPrimaryKeySelective(VoucherExchangeCodeBatchPO record);

    int updateByPrimaryKey(VoucherExchangeCodeBatchPO record);
}