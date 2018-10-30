package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.ValidateUtils;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchPageReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：充值卡兑换码批次【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rcexchangecodebatch")
public class APIRCExchangeCodeBatchController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeBatchController.class);

    @Autowired
    private RCExchangeCodeBatchBiz rcExchangeCodeBatchBiz;

    @ApiOperation(value = "【分页】查询充值卡兑换码批次信息（根据项目id和批次名称查询）", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "【分页】查询充值卡兑换码批次信息（根据项目id和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeCodeBatchPageReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum() - 1) * req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<RCExchangeCodeBatchDTO> rcExchangeCodeBatchDTOList = rcExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<RCExchangeCodeBatchResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "查询充值卡兑换码批次信息（根据项目id和批次名称查询）", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "查询充值卡兑换码批次信息（根据项目id和批次名称查询）")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll(@ModelAttribute RCExchangeCodeBatchPageReq req) {
        List<RCExchangeCodeBatchDTO> rcExchangeCodeBatchDTOList = rcExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName());
        List<RCExchangeCodeBatchResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcExchangeCodeBatchRespList);
    }

    @ApiOperation(value = "【新建】保存充值卡兑换码批次信息", httpMethod = "POST", notes = "【新建】保存充值卡兑换码批次信息")
    @RequestMapping(value = "/addsave", method = RequestMethod.POST)
    public Object addSave(@ModelAttribute RCExchangeCodeBatchAddReq req) {
        //参数检查
        if(Strings.isBlank(req.getName())||Strings.isBlank(req.getRechargeCardName())){
            throw new CommonException(RechargeCardCodeEnum.INFO_INCOMPLETE.getCode(),RechargeCardCodeEnum.INFO_INCOMPLETE.getType(),RechargeCardCodeEnum.INFO_INCOMPLETE.getMessage());
        }
        RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO = RCExchangeCodeBatchAdapter.ReqAdd2DTOAdd(req);
        rcExchangeCodeBatchBiz.addSave(rcExchangeCodeBatchAddDTO);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "生成批次下的充值卡兑换码", httpMethod = "POST", notes = "生成批次下的充值卡兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    public Object generateCode(@ModelAttribute IdReq req) {
        //参数检查,检查是否是虚拟批次，检查该批次是否已经生成过兑换码

        rcExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "充值卡兑换码批次信息回显", httpMethod = "GET", response = RCExchangeCodeBatchEditResp.class, notes = "充值卡兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchBiz.getById(req.getId());
        RCExchangeCodeBatchEditResp rcExchangeCodeBatchEditResp = RCExchangeCodeBatchAdapter.DTOEdit2RespEdit(rcExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeBatchEditResp);
    }

    @ApiOperation(value = "【修改】保存充值卡兑换码批次信息", httpMethod = "POST", notes = "【修改】保存充值卡兑换码批次信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute RCExchangeCodeBatchEditReq req) {
        //参数检查
        /**
         * 未生成码的时候，可以修改所属项目，是否是虚拟批次，面额，数量等信息
         */
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = RCExchangeCodeBatchAdapter.ReqEdit2DTOEdit(req);
        rcExchangeCodeBatchBiz.editSave(rcExchangeCodeBatchEditDTO);
        return ResponseFactory.buildSuccess();
    }


    @ApiOperation(value = "启用批次【同时改变批次下兑换码，充值卡状态】", httpMethod = "POST", notes = "启用批次【同时改变批次下兑换码，充值卡状态】")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用批次【同时改变批次下兑换码，充值卡状态】", httpMethod = "POST", notes = "停用批次【同时改变批次下兑换码，充值卡状态】")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

}
