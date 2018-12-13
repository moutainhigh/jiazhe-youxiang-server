package com.jiazhe.youxiang.server.biz.serviceitem;

import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/11.
 */
@Service("serviceItemBiz")
public class ServiceItemBiz {

    @Autowired
    private ServiceItemService serviceItemService;

    public List<ServiceItemDTO> getList() {
        return serviceItemService.getList();
    }
}
