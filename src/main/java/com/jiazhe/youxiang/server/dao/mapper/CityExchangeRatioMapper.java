package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.CityExchangeRatio;
import com.jiazhe.youxiang.server.domain.po.CityExchangeRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityExchangeRatioMapper {
    int countByExample(CityExchangeRatioExample example);

    int deleteByExample(CityExchangeRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CityExchangeRatio record);

    int insertSelective(CityExchangeRatio record);

    List<CityExchangeRatio> selectByExample(CityExchangeRatioExample example);

    CityExchangeRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CityExchangeRatio record, @Param("example") CityExchangeRatioExample example);

    int updateByExample(@Param("record") CityExchangeRatio record, @Param("example") CityExchangeRatioExample example);

    int updateByPrimaryKeySelective(CityExchangeRatio record);

    int updateByPrimaryKey(CityExchangeRatio record);
}