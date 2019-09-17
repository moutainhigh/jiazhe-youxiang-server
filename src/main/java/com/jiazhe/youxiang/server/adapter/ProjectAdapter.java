/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ProjectPO;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectUpdateDTO;
import com.jiazhe.youxiang.server.vo.req.project.ProjectAddReq;
import com.jiazhe.youxiang.server.vo.req.project.ProjectUpdateReq;
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
        projectResp.setPointConversionRate(dto.getPointConversionRate());
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
        projectAddDTO.setPointConversionRate(req.getPointConversionRate());
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
        projectPO.setPointConversionRate(projectAddDTO.getPointConversionRate());
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
        projectDTO.setPointConversionRate(po.getPointConversionRate());
        projectDTO.setStatus(po.getStatus().intValue());
        return projectDTO;
    }

    public static ProjectUpdateDTO projectUpdateReq2DTO(ProjectUpdateReq req) {
        if (req == null) {
            return null;
        }
        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        projectUpdateDTO.setId(req.getId());
        projectUpdateDTO.setName(req.getName());
        projectUpdateDTO.setDescription(req.getDescription());
        projectUpdateDTO.setPriority(req.getPriority());
        projectUpdateDTO.setPointConversionRate(req.getPointConversionRate());
        projectUpdateDTO.setStatus(req.getStatus());
        return projectUpdateDTO;
    }

    public static ProjectPO projectUpdateDTO2PO(ProjectUpdateDTO projectUpdateDTO) {
        if (projectUpdateDTO == null) {
            return null;
        }
        ProjectPO projectPO = new ProjectPO();
        projectPO.setId(projectUpdateDTO.getId());
        projectPO.setName(projectUpdateDTO.getName());
        projectPO.setDescription(projectUpdateDTO.getDescription());
        projectPO.setPriority(projectUpdateDTO.getPriority());
        projectPO.setPointConversionRate(projectUpdateDTO.getPointConversionRate());
        if(projectUpdateDTO.getStatus()!=null){
            projectPO.setStatus(projectUpdateDTO.getStatus().byteValue());
        }
        return projectPO;
    }
}