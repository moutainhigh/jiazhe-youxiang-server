package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SimpleSessionPO;
import com.jiazhe.youxiang.server.domain.po.SimpleSessionPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimpleSessionPOMapper {
    int countByExample(SimpleSessionPOExample example);

    int deleteByExample(SimpleSessionPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SimpleSessionPO record);

    int insertSelective(SimpleSessionPO record);

    List<SimpleSessionPO> selectByExample(SimpleSessionPOExample example);

    SimpleSessionPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SimpleSessionPO record, @Param("example") SimpleSessionPOExample example);

    int updateByExample(@Param("record") SimpleSessionPO record, @Param("example") SimpleSessionPOExample example);

    int updateByPrimaryKeySelective(SimpleSessionPO record);

    int updateByPrimaryKey(SimpleSessionPO record);
}