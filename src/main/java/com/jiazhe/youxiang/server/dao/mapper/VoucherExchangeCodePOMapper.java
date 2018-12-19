package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherExchangeCodePOMapper {
    int countByExample(VoucherExchangeCodePOExample example);

    int deleteByExample(VoucherExchangeCodePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoucherExchangeCodePO record);

    int insertSelective(VoucherExchangeCodePO record);

    List<VoucherExchangeCodePO> selectByExample(VoucherExchangeCodePOExample example);

    VoucherExchangeCodePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoucherExchangeCodePO record, @Param("example") VoucherExchangeCodePOExample example);

    int updateByExample(@Param("record") VoucherExchangeCodePO record, @Param("example") VoucherExchangeCodePOExample example);

    int updateByPrimaryKeySelective(VoucherExchangeCodePO record);

    int updateByPrimaryKey(VoucherExchangeCodePO record);
}