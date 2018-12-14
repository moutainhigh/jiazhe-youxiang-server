package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecode.PointExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecode.PointExchangeCodeResp;
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
 * @description：积分兑换码
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/pointexchangecode")
public class APIPointExchangeCodeController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIPointExchangeCodeController.class);


    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;

    @ApiOperation(value = "【后台】积分卡兑换码列表", httpMethod = "GET", response = PointExchangeCodeResp.class, responseContainer = "List",notes = "分页查询积分卡兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute PointExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<PointExchangeCodeResp> pointExchangeCodeBatchRespList = pointExchangeCodeDTOList.stream().map(PointExchangeCodeAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台】积分卡兑换码列表（信息查询功能模块使用）", httpMethod = "GET", response = PointExchangeCodeResp.class, responseContainer = "List",notes = "分页查询积分卡兑换码，不指定批次等信息")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute PointExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if(Strings.isBlank(req.getCode())&&Strings.isBlank(req.getKeyt())){
            req.setCode("xxxxxxxxxxxxxxxx");
            req.setKeyt("xxxxxxxxxxxxxxxx");
        }
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<PointExchangeCodeResp> pointExchangeCodeBatchRespList = pointExchangeCodeDTOList.stream().map(PointExchangeCodeAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointExchangeCodeBatchRespList, paging);
    }

//    @ApiOperation(value = "【后台】启用积分卡兑换码", httpMethod = "POST",notes = "启用积分卡兑换码，已经兑换的积分卡不能修改")
//    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "启用积分卡兑换码", level = LogLevelEnum.LEVEL_2)
//    public Object startUsing(@ModelAttribute IdReq req) {
//        //参数检查
//        CommonValidator.validateId(req);
//        pointExchangeCodeBiz.startUsing(req.getId());
//        return ResponseFactory.buildSuccess();
//    }
//
//    @ApiOperation(value = "【后台】停用积分卡兑换码", httpMethod = "POST",notes = "停用积分卡兑换码，已经兑换的积分卡不能修改")
//    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "停用积分卡兑换码", level = LogLevelEnum.LEVEL_2)
//    public Object stopUsing(@ModelAttribute IdReq req) {
//        //参数检查
//        CommonValidator.validateId(req);
//        pointExchangeCodeBiz.stopUsing(req.getId());
//        return ResponseFactory.buildSuccess();
//    }
//
//    @ApiOperation(value = "【后台】获取兑换码信息", httpMethod = "GET",response = PointExchangeCodeResp.class,notes = "获取兑换码信息")
//    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "获取兑换码信息", level = LogLevelEnum.LEVEL_1)
//    public Object getById(@ModelAttribute IdReq req) {
//        //参数检查
//        CommonValidator.validateId(req);
//        PointExchangeCodeDTO pointExchangeCodeDTO = pointExchangeCodeBiz.getById(req.getId());
//        PointExchangeCodeResp pointExchangeCodeResp = PointExchangeCodeAdapter.DTO2Resp(pointExchangeCodeDTO);
//        return ResponseFactory.buildResponse(pointExchangeCodeResp);
//    }
//
//    @ApiOperation(value = "修改兑换码信息", httpMethod = "POST",notes = "修改兑换码信息")
//    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "修改兑换码信息", level = LogLevelEnum.LEVEL_2)
//    public Object editSave(@ModelAttribute PointExchangeCodeEditReq req) {
//        //参数检查
//        CommonValidator.validateNull(req);
//        CommonValidator.validateNull(req.getId());
//        CommonValidator.validateNull(req.getRechargeCardName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
//        if(req.getExpiryTime()==0){
//            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL);
//        }
//        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
//            if(req.getRechargeCardExpiryTime()==0){
//                throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL);
//            }
//        }
//        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
//            CommonValidator.validateNull(req.getValidityPeriod(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
//        }
//        PointExchangeCodeEditDTO dto = PointExchangeCodeAdapter.EditReq2EditDTO(req);
//        pointExchangeCodeBiz.editSave(dto);
//        return ResponseFactory.buildSuccess();
//    }
//
//    @AppApi
//    @ApiOperation(value = "【APP端】客户用兑换码进行兑换", httpMethod = "POST",notes = "客户用积分卡兑换码兑换")
//    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "客户用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
//    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
//        //参数检查
//        CommonValidator.validateId(req.getId());
//        pointExchangeCodeBiz.customerSelfCharge(req.getId(),req.getKeyt());
//        return ResponseFactory.buildSuccess();
//    }
//
//    @ApiOperation(value = "【后台】后台用兑换码进行兑换", httpMethod = "POST",notes = "后台用兑换码进行兑换")
//    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
//    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "后台用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
//    public Object customerSelfCharge(@ModelAttribute CodeChargeReq req) {
//        //参数检查
//        CommonValidator.validateId(req.getId());
//        pointExchangeCodeBiz.backstageCodeCharge(req.getId(),req.getKeyt());
//        return ResponseFactory.buildSuccess();
//    }

}
