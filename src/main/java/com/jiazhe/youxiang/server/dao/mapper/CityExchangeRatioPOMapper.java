package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.CityExchangeRatioPO;
import com.jiazhe.youxiang.server.domain.po.CityExchangeRatioPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityExchangeRatioPOMapper {
    int countByExample(CityExchangeRatioPOExample example);

    int deleteByExample(CityExchangeRatioPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CityExchangeRatioPO record);

    int insertSelective(CityExchangeRatioPO record);

    List<CityExchangeRatioPO> selectByExample(CityExchangeRatioPOExample example);

    CityExchangeRatioPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CityExchangeRatioPO record, @Param("example") CityExchangeRatioPOExample example);

    int updateByExample(@Param("record") CityExchangeRatioPO record, @Param("example") CityExchangeRatioPOExample example);

    int updateByPrimaryKeySelective(CityExchangeRatioPO record);

    int updateByPrimaryKey(CityExchangeRatioPO record);
}