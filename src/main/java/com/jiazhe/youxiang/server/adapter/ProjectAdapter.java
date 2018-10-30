/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ProjectPO;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.req.project.ProjectAddReq;
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

    public static ProjectAddDTO projectAddReq2DTO(ProjectAddReq req) {
        if (req == null) {
            return null;
        }
        ProjectAddDTO projectAddDTO = new ProjectAddDTO();
        projectAddDTO.setName(req.getName());
        projectAddDTO.setDescription(req.getDescription());
        projectAddDTO.setPriority(req.getPriority());
        return projectAddDTO;
    }

    public static ProjectPO projectAddDTO2PO(ProjectAddDTO projectAddDTO) {
        if (projectAddDTO == null) {
            return null;
        }
        ProjectPO projectPO = new ProjectPO();
        projectPO.setName(projectAddDTO.getName());
        projectPO.setDescription(projectAddDTO.getDescription());
        projectPO.setPriority(projectAddDTO.getPriority());
        return projectPO;
    }

    public static ProjectDTO projectPO2DTO(ProjectPO po) {
        if (po == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(po.getId());
        projectDTO.setName(po.getName());
        projectDTO.setDescription(po.getDescription());
        projectDTO.setPriority(po.getPriority());
        projectDTO.setStatus(po.getStatus());
        return projectDTO;
    }
}