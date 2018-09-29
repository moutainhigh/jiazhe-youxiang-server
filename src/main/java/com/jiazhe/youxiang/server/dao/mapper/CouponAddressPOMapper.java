package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.CouponAddressPO;
import com.jiazhe.youxiang.server.domain.po.CouponAddressPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CouponAddressPOMapper {
    int countByExample(CouponAddressPOExample example);

    int deleteByExample(CouponAddressPOExample example);

    int deleteByPrimaryKey(String id);

    int insert(CouponAddressPO record);

    int insertSelective(CouponAddressPO record);

    List<CouponAddressPO> selectByExample(CouponAddressPOExample example);

    CouponAddressPO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CouponAddressPO record, @Param("example") CouponAddressPOExample example);

    int updateByExample(@Param("record") CouponAddressPO record, @Param("example") CouponAddressPOExample example);

    int updateByPrimaryKeySelective(CouponAddressPO record);

    int updateByPrimaryKey(CouponAddressPO record);
}