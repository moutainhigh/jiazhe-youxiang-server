package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;

import java.util.List;
import java.util.Map;


/**
 * @author TU
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePOManualMapper {

    /**
     * 插入实体，并将id返回到sysRolePO中
     * @param sysRolePO
     * @return
     */
    int insert(SysRolePO sysRolePO);


}
