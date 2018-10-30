/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/29
 */
public interface ProjectService {
    void add(ProjectAddDTO projectAddDTO);

    List<ProjectDTO> getList(String name, Integer status, Paging paging);

    ProjectDTO getById(Integer id);

    void update(Integer id, String name, String description, Integer priority, Integer status);

    void delete(Integer id);
}