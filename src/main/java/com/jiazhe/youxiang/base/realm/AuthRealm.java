package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import com.jiazhe.youxiang.server.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户的验证和授权
 * Created by TU on 2018/8/27.
 */
public class AuthRealm extends AuthorizingRealm{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;


    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*System.out.println("=========进入用户登录验证============");
        try {
            UsernamePasswordToken utoken = (UsernamePasswordToken)token;//获取用户输入的token
            String loginName = utoken.getUsername();
            SysUserPOExample sysUserPOExample = new SysUserPOExample();
            SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
            criteria.andNameEqualTo(loginName);
            criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
            List<SysUserPO> sysUserPOList = sysUserService.selectByExample(sysUserPOExample);
            if(sysUserPOList.size()==1){
                SysUserPO sysUser = sysUserPOList.get(0);
                // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                ByteSource salt = ByteSource.Util.bytes(sysUser.getSalt());
                return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                        salt,this.getName());//放入shiro.调用CredentialsMatcher检验密码
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
      return null;
    }

    //用户授权
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        /*System.out.println("=========进入用户授权============");
        //用户一个
        SysUserPO sysUserPO = (SysUserPO) principal.getPrimaryPrincipal();
        List<String> permissionList = new ArrayList<String>();
        SysUserRolePOExample sysUserRolePOExample = new SysUserRolePOExample();
        SysUserRolePOExample.Criteria criteria = sysUserRolePOExample.createCriteria();
        criteria.andUserIdEqualTo(sysUserPO.getId());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        //一个用户对应一个User_Role_list
        List<SysUserRolePO> sysUserRolePOList = sysUserRoleService.selectByExample(sysUserRolePOExample);
        //遍历User_Role_list
        for(SysUserRolePO tempSysUserRolePO:sysUserRolePOList){
            //每个User_Role对应一个Role
            SysRolePO sysRolePO = sysRoleService.findById(tempSysUserRolePO.getRoleId());
            if(sysRolePO.getIsDeleted()==0){//判断此角色是否删除
                if(sysRolePO.getIsSuper()==1){//是超级用户
                    permissionList.removeAll(permissionList);
                    permissionList.add("*");
                    break;
                }else{
                    SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
                    SysRolePermissionPOExample.Criteria criteria1 = sysRolePermissionPOExample.createCriteria();
                    criteria1.andRoleIdEqualTo(sysRolePO.getId());
                    criteria1.andIsDeletedEqualTo(Byte.valueOf("0"));
                    //每个Role对应Role_Permission_list
                    List<SysRolePermissionPO> sysRolePermissionPOList = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
                    for(SysRolePermissionPO tempSysRolePermissionPO:sysRolePermissionPOList){
                        permissionList.add(tempSysRolePermissionPO.getPermUrl());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);//将权限放入shiro中.
        return info;*/
        return null;
    }
}
