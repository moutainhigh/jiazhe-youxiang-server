package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherPOMapper {
    int countByExample(VoucherPOExample example);

    int deleteByExample(VoucherPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoucherPO record);

    int insertSelective(VoucherPO record);

    List<VoucherPO> selectByExample(VoucherPOExample example);

    VoucherPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoucherPO record, @Param("example") VoucherPOExample example);

    int updateByExample(@Param("record") VoucherPO record, @Param("example") VoucherPOExample example);

    int updateByPrimaryKeySelective(VoucherPO record);

    int updateByPrimaryKey(VoucherPO record);
}