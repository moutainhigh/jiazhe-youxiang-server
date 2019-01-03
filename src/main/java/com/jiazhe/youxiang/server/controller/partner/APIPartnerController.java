package com.jiazhe.youxiang.server.controller.partner;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.PartnerAdapter;
import com.jiazhe.youxiang.server.biz.partner.PartnerBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.resp.partner.PartnerResp;
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
@RequestMapping("api/partner")
public class APIPartnerController extends BaseController {

    @Autowired
    private PartnerBiz partnerBiz;

    @ApiOperation(value = "【后台】查询所有服务商家", httpMethod = "GET", response =PartnerResp.class, responseContainer = "List", notes = "【后台】查询所有服务商家")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "查询所有服务商家", level = LogLevelEnum.LEVEL_1)
    public Object listAll() {
        List<PartnerDTO> dtoList = partnerBiz.getList();
        List<PartnerResp> respList = dtoList.stream().map(PartnerAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(respList);
    }
}
