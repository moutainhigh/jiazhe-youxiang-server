/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.ProjectAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.ProjectPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.ProjectPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ProjectPO;
import com.jiazhe.youxiang.server.dto.project.ProjectAddDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.service.ProjectService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/29
 */
@CacheConfig(cacheNames = "project_cache")
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private ProjectPOManualMapper projectPOManualMapper;

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void add(ProjectAddDTO projectAddDTO) {
        ProjectPO projectPO = ProjectAdapter.projectAddDTO2PO(projectAddDTO);
        projectPOMapper.insertSelective(projectPO);
    }

    @Override
    public List<ProjectDTO> getList(String name, Integer status, Paging paging) {
        Integer count = projectPOManualMapper.count(name, status);
        List<ProjectPO> projectPOList = projectPOManualMapper.query(name, status, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return projectPOList.stream().map(ProjectAdapter::projectPO2DTO).collect(Collectors.toList());
    }

    @CachePut(keyGenerator = "cacheKeyGenerator")
    @Override
    public ProjectDTO getById(Integer id) {
        return ProjectAdapter.projectPO2DTO(projectPOMapper.selectByPrimaryKey(id));
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void update(Integer id, String name, String description, Integer priority, Integer status) {
        ProjectPO record = new ProjectPO();
        record.setId(id);
        if (!Strings.isBlank(name)) {
            record.setName(name);
        }
        if (!Strings.isBlank(description)) {
            record.setDescription(description);
        }
        if (priority != null) {
            record.setPriority(priority);
        }
        if (status != null) {
            record.setStatus(status.byteValue());
        }
        record.setModTime(new Date());
        projectPOMapper.updateByPrimaryKeySelective(record);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void delete(Integer id) {
        ProjectPO record = new ProjectPO();
        record.setId(id);
        record.setModTime(new Date());
        record.setIsDeleted(CommonConstant.CODE_DELETED);
        projectPOMapper.updateByPrimaryKeySelective(record);
    }
}