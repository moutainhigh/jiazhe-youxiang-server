package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.ServiceItemAdapter;
import com.jiazhe.youxiang.server.dao.mapper.ServiceItemPOMapper;
import com.jiazhe.youxiang.server.domain.po.ServiceItemPO;
import com.jiazhe.youxiang.server.domain.po.ServiceItemPOExample;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ServiceItemDTO> getList() {
        ServiceItemPOExample example = new ServiceItemPOExample();
        ServiceItemPOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<ServiceItemPO> poList = serviceItemPOMapper.selectByExample(example);
        return poList.stream().map(ServiceItemAdapter::PO2DTO).collect(Collectors.toList());
    }
}
