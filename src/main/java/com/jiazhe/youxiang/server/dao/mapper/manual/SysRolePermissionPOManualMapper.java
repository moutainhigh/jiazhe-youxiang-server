package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author TU
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePermissionPOManualMapper {

    /**
     * 批量插入
     * @param sysRolePermissionPOList
     * @return
     */
    int batchInsert(List<SysRolePermissionPO> sysRolePermissionPOList);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(@Param("ids") List<Integer> ids);
}
