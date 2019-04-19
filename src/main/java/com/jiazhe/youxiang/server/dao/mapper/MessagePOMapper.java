package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.MessagePO;
import com.jiazhe.youxiang.server.domain.po.MessagePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessagePOMapper {
    int countByExample(MessagePOExample example);

    int deleteByExample(MessagePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessagePO record);

    int insertSelective(MessagePO record);

    List<MessagePO> selectByExample(MessagePOExample example);

    MessagePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessagePO record, @Param("example") MessagePOExample example);

    int updateByExample(@Param("record") MessagePO record, @Param("example") MessagePOExample example);

    int updateByPrimaryKeySelective(MessagePO record);

    int updateByPrimaryKey(MessagePO record);
}