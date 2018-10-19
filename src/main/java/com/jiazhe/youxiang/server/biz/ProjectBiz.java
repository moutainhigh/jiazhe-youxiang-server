/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.Paging;
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

    public List<ProjectDTO> getList(Integer name, Paging paging) {
        return null;
    }

    public ProjectDTO getById(Integer id) {
        return null;
    }

    public void update(Integer id, Integer name, String description, Integer priority, Byte status) {
    }

    public void delete(Integer id) {
    }
}