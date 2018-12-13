package com.jiazhe.youxiang.server.controller.serviceitem;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.ServiceItemAdapter;
import com.jiazhe.youxiang.server.biz.serviceitem.ServiceItemBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.resp.serviceitem.ServiceItemResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/12/11.
 */
@RestController
@RequestMapping("api/serviceitem")
public class APIServiceItemController extends BaseController{

    @Autowired
    private ServiceItemBiz serviceItemBiz;

    @ApiOperation(value = "【后台】查询所有服务项目", httpMethod = "GET", response =ServiceItemResp.class, responseContainer = "List", notes = "【后台】查询所有服务项目")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "查询所有服务项目", level = LogLevelEnum.LEVEL_1)
    public Object listAll() {
        List<ServiceItemDTO> dtoList = serviceItemBiz.getList();
        List<ServiceItemResp> respList = dtoList.stream().map(ServiceItemAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(respList);
    }
}
