package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PageFormatUtil;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePOExample;
import com.jiazhe.youxiang.server.vo.page.SysRolePageResp;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import com.jiazhe.youxiang.server.vo.resp.SysRoleResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @ApiOperation(value = "list", httpMethod = "GET", response = SysRoleReq.class, notes = "查询角色列表，并分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object demo(@ModelAttribute SysRoleReq req) {
        SysRolePageResp result = new SysRolePageResp();
        int count = sysRolePOManualMapper.count(req);
        List<Map> maps = sysRolePOManualMapper.getPageContent(req);
        result.setDataRows(PageFormatUtil.format(maps));
        result.setCurrPage(req.getPageNum());
        result.setTotalCount(count);
        result.setTotalPage((int) Math.ceil(count*1.0/req.getPageSize()));
        return result;
    }
}
