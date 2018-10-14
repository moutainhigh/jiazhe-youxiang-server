package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;


/**
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePOManualMapper {

    int count(SysRoleReq req);

    List<Map> getPageContent(SysRoleReq req);

}
