/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.service.ProjectService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
@Service("projectBiz")
public class ProjectBiz {

    @Autowired
    private ProjectService projectService;

    public void add(ProjectAddDTO projectAddDTO) {
        projectService.add(projectAddDTO);
    }

    public List<ProjectDTO> getList(String name, Integer status, Paging paging) {
        return projectService.getList(name, status, paging);
    }

    public ProjectDTO getById(Integer id) {
        return projectService.getById(id);
    }

    public void update(Integer id, String name, String description, Integer priority, Integer status) {
        projectService.update(id, name, description, priority, status);
    }

    public void delete(Integer id) {
        projectService.delete(id);
    }


}