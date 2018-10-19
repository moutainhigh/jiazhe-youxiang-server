/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProjectAdapter {
    public static ProjectResp projectDTO2VO(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        ProjectResp projectResp = new ProjectResp();
        projectResp.setId(dto.getId());
        projectResp.setName(dto.getName());
        projectResp.setDescription(dto.getDescription());
        projectResp.setPriority(dto.getPriority());
        projectResp.setStatus(dto.getStatus());
        return projectResp;
    }
}