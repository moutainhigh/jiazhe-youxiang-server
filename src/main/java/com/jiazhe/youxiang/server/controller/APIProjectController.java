/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.ProjectAdapter;
import com.jiazhe.youxiang.server.biz.ProjectBiz;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectAddReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectListReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class APIProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIProjectController.class);

    @Autowired
    private ProjectBiz projectBiz;


    /**
     * 添加项目
     *
     * @return
     */
    @ApiOperation(value = "添加项目", httpMethod = "POST", notes = "添加项目")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object add(@ModelAttribute ProjectAddReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateNull(req);
        //CommonValidator.validateNull(req.getName());
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
    public Object getList(@ModelAttribute ProjectListReq req) {
        //TODO niexiao 参数验证
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        //调用BIZ方法
        List<ProjectDTO> projectDTOList = projectBiz.getList(req.getName(), paging);
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
    @ApiOperation(value = "编辑项目信息", httpMethod = "POST", notes = "编辑项目信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@ModelAttribute ProjectUpdateReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getId());
        //调用BIZ方法
        projectBiz.update(req.getId(), req.getName(), req.getDescription(), req.getPriority(), req.getStatus());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除项目
     *
     * @return
     */
    @ApiOperation(value = "删除项目", httpMethod = "GET", notes = "删除项目")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        projectBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }
}