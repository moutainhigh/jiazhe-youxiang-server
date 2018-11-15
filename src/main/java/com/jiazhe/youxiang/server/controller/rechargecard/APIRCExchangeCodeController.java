package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;
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
 * @description：充值卡兑换码【rc是rechargecard缩写】
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/rcexchangecode")
public class APIRCExchangeCodeController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeController.class);

    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @ApiOperation(value = "【后台】充值卡兑换码列表", httpMethod = "GET", response = RCExchangeCodeResp.class, responseContainer = "List",notes = "分页查询充值卡兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<RCExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(RCExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台】充值卡兑换码列表（信息查询功能模块使用）", httpMethod = "GET", response = RCExchangeCodeResp.class, responseContainer = "List",notes = "分页查询充值卡兑换码，不指定批次等信息")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    public Object searchListPage(@ModelAttribute RCExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if(Strings.isBlank(req.getCode())&&Strings.isBlank(req.getKeyt())){
            req.setCode("xxxxxxxxxxxxxxxx");
            req.setKeyt("xxxxxxxxxxxxxxxx");
        }
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<RCExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(RCExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台】启用充值卡兑换码", httpMethod = "POST",notes = "启用充值卡兑换码，已经兑换的充值卡不能修改")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】停用充值卡兑换码", httpMethod = "POST",notes = "停用充值卡兑换码，已经兑换的充值卡不能修改")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取兑换码信息", httpMethod = "GET",response = RCExchangeCodeResp.class,notes = "获取兑换码信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        RCExchangeCodeDTO rcExchangeCodeDTO = rcExchangeCodeBiz.getById(req.getId());
        RCExchangeCodeResp rcExchangeCodeResp = RCExchangeCodeAdapter.DTO2Resp(rcExchangeCodeDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeResp);
    }

    @ApiOperation(value = "修改兑换码信息", httpMethod = "POST",notes = "修改兑换码信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute RCExchangeCodeEditReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getRechargeCardName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        CommonValidator.validateNull(req.getExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL));
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            CommonValidator.validateNull(req.getRechargeCardExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
        }
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
        }
        RCExchangeCodeEditDTO dto = RCExchangeCodeAdapter.EditReq2EditDTO(req);
        rcExchangeCodeBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

    @AppApi
    @ApiOperation(value = "【APP端】客户用兑换码进行兑换", httpMethod = "POST",notes = "客户用充值卡兑换码兑换，这里为了与后台共用接口，所以传参为客户电话")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        CommonValidator.validateMobile(req.getMobile(),new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        rcExchangeCodeBiz.customerSelfCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】后台用兑换码进行兑换", httpMethod = "POST",notes = "后台用兑换码进行兑换")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    public Object customerSelfCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        CommonValidator.validateMobile(req.getMobile(),new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        rcExchangeCodeBiz.backstageCodeCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

}
