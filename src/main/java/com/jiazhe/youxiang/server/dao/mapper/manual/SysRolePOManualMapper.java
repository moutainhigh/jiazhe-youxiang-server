package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import java.util.List;
import java.util.Map;


/**
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePOManualMapper {

    //根据条件查询记录条数
    int count(SysRoleReq req);

    //根据条件查询，返回分页结果
    List<Map> getPageContent(SysRoleReq req);

    //插入实体，并隐式返回id
    int insert(SysRolePO sysRolePO);


}
