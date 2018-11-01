package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;
import io.swagger.annotations.ApiOperation;
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
 * @description：充值卡兑换码【rc是rechargecard缩写】
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/rcexchangecode")
public class APIRCExchangeCodeController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeController.class);

    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @ApiOperation(value = "分页查询充值卡兑换码（根据批次id和兑换码的码和密钥查询）", httpMethod = "GET", response = RCExchangeCodeResp.class, responseContainer = "List",notes = "分页查询充值卡兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<RCExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(RCExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "启用充值卡兑换码", httpMethod = "POST",notes = "启用充值卡兑换码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用充值卡兑换码", httpMethod = "POST",notes = "停用充值卡兑换码")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改充值卡兑换码过期时间", httpMethod = "POST",notes = "修改充值卡兑换码过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        rcExchangeCodeBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "客户用用兑换码自行充值", httpMethod = "POST",notes = "客户用用兑换码自行充值")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        rcExchangeCodeBiz.customerSelfCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "后台用兑换码进行充值", httpMethod = "POST",notes = "后台用兑换码进行充值")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    public Object customerSelfCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        rcExchangeCodeBiz.backstageCodeCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

}
