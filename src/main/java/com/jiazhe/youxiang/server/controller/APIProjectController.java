/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.ProjectAdapter;
import com.jiazhe.youxiang.server.biz.ProjectBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.ProjectCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ProjectException;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectAddReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectListReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目管理Controller
 *
 * @author niexiao
 * @created 2018/10/18
 */
@RestController
@RequestMapping("api/project")
public class APIProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIProjectController.class);

    @Autowired
    private ProjectBiz projectBiz;


    /**
     * 添加项目
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_ADD)
    @ApiOperation(value = "添加项目", httpMethod = "POST", notes = "添加项目")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "添加项目", level = LogLevelEnum.LEVEL_2)
    public Object add(@ModelAttribute ProjectAddReq req) {
        CommonValidator.validateNull(req);
        validateProjectName(req.getName());
        validatePointConversionRate(req.getPointConversionRate());
        ProjectAddDTO projectAddDTO = ProjectAdapter.projectAddReq2DTO(req);
        //调用BIZ方法
        projectBiz.add(projectAddDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 获得某一具体项目信息
     *
     * @return
     */
    @ApiOperation(value = "获得某一具体项目信息", httpMethod = "GET", response = ProjectResp.class, notes = "获得某一具体项目信息")
    @RequestMapping(value = "getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "获得某一具体项目信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProjectDTO projectDTO = projectBiz.getById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProjectAdapter.projectDTO2VO(projectDTO));
    }

    /**
     * 查询项目信息
     *
     * @return
     */
    @ApiOperation(value = "查询项目信息", httpMethod = "GET", response = ProjectResp.class, responseContainer = "List", notes = "查询项目信息")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "查询项目信息", level = LogLevelEnum.LEVEL_1)
    public Object getList(@ModelAttribute ProjectListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<ProjectDTO> projectDTOList = projectBiz.getList(req.getName(), req.getStatus(), paging);
        //将DTO转成VO
        List<ProjectResp> result = projectDTOList.stream().map(ProjectAdapter::projectDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 编辑项目信息
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_EDIT)
    @ApiOperation(value = "编辑项目信息", httpMethod = "POST", notes = "编辑项目信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "编辑项目信息", level = LogLevelEnum.LEVEL_2)
    public Object update(@ModelAttribute ProjectUpdateReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getId());
        validateProjectName(req.getName());
        validatePointConversionRate(req.getPointConversionRate());
        ProjectUpdateDTO projectUpdateDTO = ProjectAdapter.projectUpdateReq2DTO(req);
        //调用BIZ方法
        projectBiz.update(projectUpdateDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除项目
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_DELETE)
    @ApiOperation(value = "删除项目", httpMethod = "POST", notes = "删除项目")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "删除项目", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        projectBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /**
     * 启动项目
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_STATUS_CHANGE)
    @ApiOperation(value = "启动项目", httpMethod = "GET", response = ProjectResp.class, notes = "启动项目")
    @RequestMapping(value = "begin", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PROJECT, operate = "启动项目", level = LogLevelEnum.LEVEL_2)
    public Object begin(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        projectBiz.begin(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /**
     * 停止项目
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_STATUS_CHANGE)
    @ApiOperation(value = "停止项目", httpMethod = "GET", response = ProjectResp.class, notes = "停止项目")
    @RequestMapping(value = "end", method = RequestMethod.GET)
    public Object end(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        projectBiz.end(req.getId());
        return ResponseFactory.buildSuccess();
    }

    private static void validatePointConversionRate(BigDecimal rate) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProjectException(ProjectCodeEnum.PROJECT_POINT_CONVERSION_RATE_ERROR);

        }
    }

    private static void validateProjectName(String name) {
        if (Strings.isBlank(name)) {
            throw new ProjectException(ProjectCodeEnum.PROJECT_NAME_IS_NULL);
        }
    }

}