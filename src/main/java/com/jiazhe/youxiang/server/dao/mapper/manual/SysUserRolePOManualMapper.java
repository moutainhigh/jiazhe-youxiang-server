package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public interface SysUserRolePOManualMapper {

    int batchDelete(@Param("ids")List<Integer> ids);

    int batchInsert(List<SysUserRolePO> newRolesPO);
}
