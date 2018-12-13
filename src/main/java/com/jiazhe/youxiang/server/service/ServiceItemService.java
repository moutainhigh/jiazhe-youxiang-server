package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public interface ServiceItemService {

    ServiceItemDTO getById(Integer serviceItemId);

    List<ServiceItemDTO> getList();
}
