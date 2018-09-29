package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户的验证和授权
 * Created by TU on 2018/8/27.
 */
public class AuthRealm extends AuthorizingRealm{

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            UsernamePasswordToken utoken = (UsernamePasswordToken)token;//获取用户输入的token
            String loginName = utoken.getUsername();
            SysUserPO sysUserPO = operatorService.findByLoginName(loginName);
            if (sysUserPO != null && sysUserPO.getIsDeleted()==0 ) {
                // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                return new SimpleAuthenticationInfo(sysUserPO, sysUserPO.getPassword(),
                        this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }

    //用户授权
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
       /* Operator operator = (Operator) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions = new ArrayList<String>();
        Role role = roleService.findById(operator.getRoleId()==null?"":operator.getRoleId());

        String rolePermission = "";//角色权限
        String departmentPermission = "";//部门权限
        if((personalPermission!=null)&&(!personalPermission.equals(""))){
            allPermission.add(personalPermission);
        }
        if(role!=null){
            rolePermission = role.getPermissionStr();
            if((rolePermission!=null)&&(!rolePermission.equals(""))){
                allPermission.add(rolePermission);
            }
        }
        if(department!=null){
            departmentPermission = department.getPermissionStr();
            if((departmentPermission!=null)&&(!departmentPermission.equals(""))){
                allPermission.add(departmentPermission);
            }
        }
        for(String temp : allPermission){
            String[] tempArray = temp.split(";");
            for(String temp1 : tempArray){
                permissions.add(temp1);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;*//*
        return null;*/
    }
}
