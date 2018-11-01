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

    void batchDelete(@Param("ids")List<Integer> ids);

    void batchInsert(List<SysUserRolePO> newRolesPO);
}
