package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserPOMapper {
    int countByExample(SysUserPOExample example);

    int deleteByExample(SysUserPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserPO record);

    int insertSelective(SysUserPO record);

    List<SysUserPO> selectByExample(SysUserPOExample example);

    SysUserPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserPO record, @Param("example") SysUserPOExample example);

    int updateByExample(@Param("record") SysUserPO record, @Param("example") SysUserPOExample example);

    int updateByPrimaryKeySelective(SysUserPO record);

    int updateByPrimaryKey(SysUserPO record);
}