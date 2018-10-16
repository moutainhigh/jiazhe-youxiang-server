package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PageFormatUtil;
import com.jiazhe.youxiang.base.util.ValidateUtils;
import com.jiazhe.youxiang.server.biz.SysRoleBiz;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import com.jiazhe.youxiang.server.vo.resp.SysRoleResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 后台所有关于角色的接口
 * Created by tujia on 2018/10/14.
 */
@RestController
@RequestMapping("api/sysrole")
public class APISysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysRoleController.class);

    @Autowired
    private SysRoleBiz sysRoleBiz;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @ApiOperation(value = "list", httpMethod = "GET", response = SysRoleResp.class, notes = "查询角色列表，并分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        int count = sysRoleService.count(req);
        List<Map> maps = sysRoleService.getPageContent(req);
        result.setDataRows(PageFormatUtil.format(maps));
        result.setCurrPage(req.getPageNum());
        result.setTotalCount(count);
        result.setTotalPage((int) Math.ceil(count * 1.0 / req.getPageSize()));
        return result;
    }

    @ApiOperation(value = "save", httpMethod = "GET", response = SysRoleResp.class, notes = "保存角色信息")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Object save(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();//返回
        SysRolePO sysRolePO;
        List<SysRolePermissionPO> oldPerms = new ArrayList<SysRolePermissionPO>();
        List<SysRolePermissionPO> newPerms = new ArrayList<SysRolePermissionPO>();
        boolean isAdd;
        String code = "000000";
        String msg = "";
        try {
            req.setPerms(req.getPerms().trim());//去空格
            if ((req.getIsSuper().equals("false") && req.getPerms().length() < 1) || req.getName().equals("")) {
                msg = "信息填写不完整";
                throw new Exception(msg);
            }
            if (!ValidateUtils.isNumeric(req.getPriority())) {
                msg = "排序项必为整数类型";
                throw new Exception(msg);
            }
            String[] perms = req.getPerms().length() < 1 ? null : req.getPerms().split(",");//新的权限字符串
            if (req.getId() == 0) {//新建
                isAdd = true;
                sysRolePO = new SysRolePO();
                sysRolePO.setExtInfo("");
                sysRolePO.setIsDeleted(Byte.valueOf("0"));
                sysRolePO.setAddTime(new Date());
                if (req.getIsSuper().equals("false")) {//非管理员，添加role_permission
                    for (String perm : perms) {
                        SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
                        sysRolePermissionPO.setPermUrl(perm);
                        sysRolePermissionPO.setExtInfo("");
                        sysRolePermissionPO.setIsDeleted(Byte.valueOf("0"));
                        sysRolePermissionPO.setAddTime(new Date());
                        sysRolePermissionPO.setModTime(new Date());
                        newPerms.add(sysRolePermissionPO);
                    }
                }
            } else {//修改
                isAdd = false;
                sysRolePO = sysRoleService.findById(req.getId());
                SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
                SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
                criteria.andRoleIdEqualTo(req.getId());
                criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
                //修改前的权限
                oldPerms = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
                if (null != perms) {
                    for (String perm : perms) {//遍历新的权限String[]
                        boolean has = false;
                        for (SysRolePermissionPO temp : oldPerms) {//判断该权限是否已经存在
                            if (temp.getPermUrl().equals(perm)) {//已经存在
                                Iterator<SysRolePermissionPO> iterator = oldPerms.iterator();
                                while (iterator.hasNext()) {
                                    SysRolePermissionPO temp1 = iterator.next();
                                    if (temp.getId().equals(temp1.getId())) {
                                        iterator.remove();//将未修改的移出来
                                    }
                                }
                                has = true;
                                break;
                            }
                        }
                        if (!has) {
                            SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
                            sysRolePermissionPO.setRoleId(req.getId());
                            sysRolePermissionPO.setPermUrl(perm);
                            sysRolePermissionPO.setExtInfo("");
                            sysRolePermissionPO.setIsDeleted(Byte.valueOf("0"));
                            sysRolePermissionPO.setAddTime(new Date());
                            sysRolePermissionPO.setModTime(new Date());
                            newPerms.add(sysRolePermissionPO);
                        }
                    }
                }
            }
            sysRolePO.setName(req.getName());
            sysRolePO.setIsSuper(Byte.valueOf(req.getIsSuper().equals("true") ? "1" : "0"));
            sysRolePO.setPriority(Integer.valueOf(req.getPriority()));
            sysRolePO.setModTime(new Date());
            int count = sysRoleBiz.save(isAdd, sysRolePO, newPerms, oldPerms);
        } catch (Exception e) {
            code = "000003";
        } finally {
            result.setCode(code);
            result.setMsg(msg);
        }
        return result;
    }

    @ApiOperation(value = "getbyid", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id获取角色信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        result.setCode("000000");
        result.setMsg("获取成功");
        SysRolePO sysRolePO = sysRoleService.findById(req.getId());
        //查询所有权限字符串
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(req.getId());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> perms = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
        StringBuilder permsStr = new StringBuilder();
        for (SysRolePermissionPO perm : perms) {
            permsStr.append(perm.getPermUrl() + ",");
        }
        if (perms.size() > 0) {
            permsStr.deleteCharAt(permsStr.length() - 1);
        }
        result.setId(sysRolePO.getId());
        result.setName(sysRolePO.getName());
        result.setPriority(sysRolePO.getPriority());
        result.setPermsStr(permsStr.toString());
        result.setIsSuper(sysRolePO.getIsSuper() == Byte.valueOf("1") ? "true" : "false");
        return result;
    }

    @ApiOperation(value = "delete", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id删除角色信息")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        result.setCode("000000");
        result.setMsg("删除成功");
        SysRolePO sysRolePO = sysRoleService.findById(req.getId());
        sysRolePO.setIsDeleted(Byte.valueOf("1"));
        sysRolePO.setModTime(new Date());
        //查询所有权限字符串
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(req.getId());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> perms = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
        for (SysRolePermissionPO temp : perms) {
            temp.setIsDeleted(Byte.valueOf("1"));
            temp.setModTime(new Date());
        }
        sysRoleService.update(sysRolePO);
        if (perms.size() > 0) {
            sysRolePermissionService.batchUpdate(perms);
        }
        return result;
    }
}
