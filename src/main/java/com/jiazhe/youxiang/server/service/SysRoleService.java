package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;

import java.util.List;
import java.util.Map;

/**
 * Created by TU on 2018/10/15.
 */
public interface SysRoleService {

    //根据条件查询角色总数量
    int count(SysRoleReq req);

    //根据条件查询当前页的数据视图
    List<Map> getPageContent(SysRoleReq req);

    //根据id查询角色
    SysRolePO findById(int id);

    //根据实体更新
    int update(SysRolePO sysRolePO);

    //插入并返回将id返回到sysRolePO中
    int insert(SysRolePO sysRolePO);
}
