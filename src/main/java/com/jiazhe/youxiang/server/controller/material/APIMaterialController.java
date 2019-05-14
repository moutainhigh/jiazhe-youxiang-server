package com.jiazhe.youxiang.server.controller.material;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.material.MaterialAdapter;
import com.jiazhe.youxiang.server.biz.material.MaterialBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.MaterialCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.MaterialException;
import com.jiazhe.youxiang.server.dto.material.MaterialDto;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.material.MaterialPageReq;
import com.jiazhe.youxiang.server.vo.req.material.MaterialSaveReq;
import com.jiazhe.youxiang.server.vo.req.material.MaterialSummaryPageReq;
import com.jiazhe.youxiang.server.vo.resp.material.MaterialResp;
import com.jiazhe.youxiang.server.vo.resp.material.MaterialSummaryResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    @RequestMapping(value = "/listsummarypage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MATERIAL, operate = "物料汇总列表", level = LogLevelEnum.LEVEL_1)
    public Object listSummaryPage(@ModelAttribute MaterialSummaryPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<MaterialSummaryDto> dtoList = materialBiz.getSummaryList(req.getPayerIds(), req.getPayeeIds(), paging);
        List<MaterialSummaryResp> respList = dtoList.stream().map(MaterialAdapter::summaryDto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "转账明细列表", httpMethod = "GET", response = MaterialResp.class, responseContainer = "List", notes = "转账明细列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MATERIAL, operate = "转账明细列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute MaterialPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date transferTimeBegin = req.getTransferTimeBegin() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getFirstSecond(req.getTransferTimeBegin()));
        Date transferTimeEnd = req.getTransferTimeEnd() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getLastSecond(req.getTransferTimeEnd()));
        List<MaterialDto> dtoList = materialBiz.getList(req.getPayerIds(), req.getPayeeIds(), transferTimeBegin, transferTimeEnd, paging);
        List<MaterialResp> respList = dtoList.stream().map(MaterialAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "删除转账记录", httpMethod = "POST", notes = "删除转账记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.MATERIAL, operate = "删除转账记录", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        materialBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "物料汇总", httpMethod = "GET", response = MaterialSummaryResp.class, responseContainer = "List", notes = "物料汇总")
    @RequestMapping(value = "/calculatesummary", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MATERIAL, operate = "物料汇总", level = LogLevelEnum.LEVEL_1)
    public Object calculateSummary(@ModelAttribute MaterialSummaryPageReq req) {
        MaterialSummaryDto dto = materialBiz.calculateSummary(req.getPayerIds(), req.getPayeeIds());
        MaterialSummaryResp resp = MaterialAdapter.summaryDto2Resp(dto);
        return ResponseFactory.buildResponse(resp);
    }

    @ApiOperation(value = "保存转账信息", httpMethod = "POST", notes = "保存转账信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.MATERIAL, operate = "保存转账信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute MaterialSaveReq req) {
        CommonValidator.validateId(req.getPayeeId(), new MaterialException(MaterialCodeEnum.PAYEE_NOT_EXIST));
        CommonValidator.validateNull(req.getTransferAmount(), new MaterialException(MaterialCodeEnum.TRANSFER_AMOUNT_ILLEGAL));
        CommonValidator.validateNull(req.getMaterialValue(), new MaterialException(MaterialCodeEnum.MATERIAL_VALUE_ILLEGAL));
        if (CommonConstant.NULL_TIME == req.getTransferTime()) {
            throw new MaterialException(MaterialCodeEnum.TRANSFER_TIME_IS_NULL);
        }
        materialBiz.save(req.getPayeeId(), req.getTransferAmount(), req.getMaterialValue(), new Date(req.getTransferTime()), req.getRemark());
        return ResponseFactory.buildSuccess();
    }


}
