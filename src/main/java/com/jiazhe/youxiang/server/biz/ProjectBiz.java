/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.common.enums.ProjectStatusEnum;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectUpdateDTO;
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

    public void update(ProjectUpdateDTO projectUpdateDTO) {
        projectService.update(projectUpdateDTO);
    }

    public void delete(Integer id) {
        projectService.delete(id);
    }


    public void begin(Integer id) {
        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        projectUpdateDTO.setId(id);
        projectUpdateDTO.setStatus(ProjectStatusEnum.DOING.getId());
        projectService.update(projectUpdateDTO);
    }

    public void end(Integer id) {
        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        projectUpdateDTO.setId(id);
        projectUpdateDTO.setStatus(ProjectStatusEnum.FINISH.getId());
        projectService.update(projectUpdateDTO);
    }


}