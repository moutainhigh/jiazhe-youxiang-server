package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PageFormatUtil;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOMapper;
import com.jiazhe.youxiang.server.domain.po.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台所有关于角色的接口
 * Created by tujia on 2018/10/14.
 */
@RestController
@RequestMapping("api/sysrole")
public class APISysRoleController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysRoleController.class);

    @Autowired
    private SysRolePOMapper sysRolePOMapper;
    @Autowired
    private SysRolePOManualMapper sysRolePOManualMapper;
    @Autowired
    private SysRolePermissionPOMapper sysRolePermissionPOMapper;
    @Autowired
    private SysRolePermissionPOManualMapper sysRolePermissionPOManualMapper;

    @ApiOperation(value = "list", httpMethod = "GET", response = SysRoleResp.class, notes = "查询角色列表，并分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        int count = sysRolePOManualMapper.count(req);
        List<Map> maps = sysRolePOManualMapper.getPageContent(req);
        result.setDataRows(PageFormatUtil.format(maps));
        result.setCurrPage(req.getPageNum());
        result.setTotalCount(count);
        result.setTotalPage((int) Math.ceil(count*1.0/req.getPageSize()));
        return result;
    }

    @ApiOperation(value = "save", httpMethod = "GET", response = SysRoleResp.class, notes = "保存角色信息")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Object save(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        result.setCode("000000");
        result.setMsg("保存成功");
        if(req.getId()==0){//新建
            SysRolePO sysRolePO = new SysRolePO();
            sysRolePO.setName(req.getName());
            sysRolePO.setIsSuper(Byte.valueOf(req.getIsSuper().equals("true")?"1":"0"));
            sysRolePO.setPriority(req.getPriority());
            sysRolePO.setExtInfo("");
            sysRolePO.setIsDeleted(Byte.valueOf("0"));
            sysRolePO.setAddTime(new Date());
            sysRolePO.setModTime(new Date());
            sysRolePOManualMapper.insert(sysRolePO);
            if(req.getIsSuper().equals("false")){//添加role_permission
                if(req.getPerms().length()>1){
                    List<SysRolePermissionPO> sysRolePermissionPOList = new ArrayList<SysRolePermissionPO>() ;
                    String[] perms = req.getPerms().split(",");
                    for(String perm : perms){
                        SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
                        sysRolePermissionPO.setRoleId(sysRolePO.getId());
                        sysRolePermissionPO.setPermUrl(perm);
                        sysRolePermissionPO.setExtInfo("");
                        sysRolePermissionPO.setIsDeleted(Byte.valueOf("0"));
                        sysRolePermissionPO.setAddTime(new Date());
                        sysRolePermissionPO.setModTime(new Date());
                        sysRolePermissionPOList.add(sysRolePermissionPO);
                    }
                    sysRolePermissionPOManualMapper.batchInsert(sysRolePermissionPOList);
                }
            }
        }else{//修改
            SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(req.getId());
            sysRolePO.setName(req.getName());
            sysRolePO.setIsSuper(Byte.valueOf(req.getIsSuper().equals("true")?"1":"0"));
            sysRolePO.setPriority(req.getPriority());
            sysRolePO.setModTime(new Date());
            sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
            if(req.getPerms().length()>1){
                String[] perms = req.getPerms().split(",");
                //查询所有旧的权限字符串
                SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
                SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
                criteria.andRoleIdEqualTo(req.getId());
                List<SysRolePermissionPO> oldPerms = sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
                List<SysRolePermissionPO> newPerms = new ArrayList<SysRolePermissionPO>();
                for(SysRolePermissionPO temp : oldPerms){//先设置旧的都删除掉
                    temp.setIsDeleted(Byte.valueOf("1"));
                }
                for(String perm : perms){
                    boolean has = false;
                    for(SysRolePermissionPO temp : oldPerms){
                        if(temp.getPermUrl().equals(perm)){
                            temp.setIsDeleted(Byte.valueOf("0"));
                            temp.setModTime(new Date());
                            has = true;
                            break;
                        }
                    }
                    if(!has){
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
                if(newPerms.size()>0){
                    sysRolePermissionPOManualMapper.batchInsert(newPerms);
                }
                if(oldPerms.size()>0){
                    sysRolePermissionPOManualMapper.batchUpdate(oldPerms);
                }
            }
        }
       return result;
    }

    @ApiOperation(value = "getbyid", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id获取角色信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        result.setCode("000000");
        result.setMsg("获取成功");
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(req.getId());
        //查询所有权限字符串
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(req.getId());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> perms = sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
        StringBuilder permsStr = new StringBuilder();
        for(SysRolePermissionPO perm : perms){
            permsStr.append(perm.getPermUrl()+",");
        }
        if(perms.size()>0){
            permsStr.deleteCharAt(permsStr.length()-1);
        }
        result.setId(sysRolePO.getId());
        result.setName(sysRolePO.getName());
        result.setPriority(sysRolePO.getPriority());
        result.setPermsStr(permsStr.toString());
        result.setIsSuper(sysRolePO.getIsSuper()==Byte.valueOf("1")?"true":"false");
        return result;
    }

    @ApiOperation(value = "delete", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id删除角色信息")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute SysRoleReq req) {
        SysRoleResp result = new SysRoleResp();
        result.setCode("000000");
        result.setMsg("删除成功");
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(req.getId());
        sysRolePO.setIsDeleted(Byte.valueOf("1"));
        sysRolePO.setModTime(new Date());
        //查询所有权限字符串
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(req.getId());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> perms = sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
        for(SysRolePermissionPO temp : perms){
            temp.setIsDeleted(Byte.valueOf("1"));
            temp.setModTime(new Date());
        }
        sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
        sysRolePermissionPOManualMapper.batchUpdate(perms);
        return result;
    }
}
