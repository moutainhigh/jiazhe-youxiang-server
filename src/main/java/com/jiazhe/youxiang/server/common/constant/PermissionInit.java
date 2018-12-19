package com.jiazhe.youxiang.server.common.constant;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.common.enums.PermissionTreeEnum;
import com.jiazhe.youxiang.server.vo.resp.sysrole.PermissionTreeResp;

import java.util.List;

/**
 * @author tu
 * @description：初始化权限树
 * @date 2018/12/15
 */
public class PermissionInit  {

    public static List<PermissionTreeResp> treeRespList = Lists.newArrayList();

    static{
        for (PermissionTreeEnum temp : PermissionTreeEnum.values()) {
            PermissionTreeResp tree = new PermissionTreeResp();
            tree.setId(temp.getId());
            tree.setpId(temp.getpId());
            tree.setName(temp.getName());
            tree.setPerm(temp.getPerm());
            treeRespList.add(tree);
        }
    }
}
