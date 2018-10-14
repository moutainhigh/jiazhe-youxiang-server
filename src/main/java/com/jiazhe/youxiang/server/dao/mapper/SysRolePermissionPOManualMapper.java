package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import java.util.List;
import java.util.Map;


/**
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePermissionPOManualMapper {

    int batchInsert(List<SysRolePermissionPO> sysRolePermissionPOList);

    int batchUpdate(List<SysRolePermissionPO> sysRolePermissionPOList);

}
