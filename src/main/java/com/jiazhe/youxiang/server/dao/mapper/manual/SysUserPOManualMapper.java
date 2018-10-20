package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public interface SysUserPOManualMapper {

    Integer count(@Param("name")String name);

    List<SysUserPO> query(@Param("name")String name, @Param("offset")Integer offset, @Param("limit")Integer limit);

}
