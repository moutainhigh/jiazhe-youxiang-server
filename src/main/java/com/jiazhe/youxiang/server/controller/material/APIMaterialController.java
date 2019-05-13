package com.jiazhe.youxiang.server.controller.material;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.material.MaterialAdapter;
import com.jiazhe.youxiang.server.biz.material.MaterialBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.material.MaterialPageReq;
import com.jiazhe.youxiang.server.vo.resp.material.MaterialSummaryResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2019-05-12
 */
@RestController
@RequestMapping("api/material")
public class APIMaterialController extends BaseController {

    @Autowired
    private MaterialBiz materialBiz;

    @ApiOperation(value = "物料汇总列表", httpMethod = "GET", response = MaterialSummaryResp.class, responseContainer = "List", notes = "物料汇总列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "物料汇总列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute MaterialPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<MaterialSummaryDto> dtoList = materialBiz.getSummaryList(req.getPayerIds(),req.getPayeeIds(),paging);
        List<MaterialSummaryResp> respList = dtoList.stream().map(MaterialAdapter::summaryDto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

}
