package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.ServiceItemAdapter;
import com.jiazhe.youxiang.server.dao.mapper.ServiceItemPOMapper;
import com.jiazhe.youxiang.server.domain.po.ServiceItemPO;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
@Service("serviceItemService")
@Transactional(rollbackFor = Exception.class)
public class ServiceItemServiceImpl implements ServiceItemService {
    @Autowired
    private ServiceItemPOMapper serviceItemPOMapper;

    @Override
    public ServiceItemDTO getById(Integer serviceItemId) {
        ServiceItemPO po = serviceItemPOMapper.selectByPrimaryKey(serviceItemId);
        return ServiceItemAdapter.PO2DTO(po);
    }
}
