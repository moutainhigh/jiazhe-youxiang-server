package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.MessageTemplatePO;
import com.jiazhe.youxiang.server.domain.po.MessageTemplatePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTemplatePOMapper {
    int countByExample(MessageTemplatePOExample example);

    int deleteByExample(MessageTemplatePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageTemplatePO record);

    int insertSelective(MessageTemplatePO record);

    List<MessageTemplatePO> selectByExample(MessageTemplatePOExample example);

    MessageTemplatePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageTemplatePO record, @Param("example") MessageTemplatePOExample example);

    int updateByExample(@Param("record") MessageTemplatePO record, @Param("example") MessageTemplatePOExample example);

    int updateByPrimaryKeySelective(MessageTemplatePO record);

    int updateByPrimaryKey(MessageTemplatePO record);
}